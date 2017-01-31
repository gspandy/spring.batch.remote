/**
 * 
 */
package c.z.ignite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteAtomicSequence;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;

/**
 * @author sunff
 *
 */
public class IdGenerator {

	private static final ThreadLocal<Connection> local = new ThreadLocal<Connection>();

	public static Connection getConnection() {

		if (local.get() != null) {
			return local.get();
		}
		Connection conn = null;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test";
		String username = "root";
		String password = "root";

		try {
			Class.forName(driver); // classLoader,加载对应驱动
			conn = (Connection) DriverManager.getConnection(url, username,
					password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		local.set(conn);
		return conn;
	}

	private static void insertId(Long id, String threadId) throws SQLException {
		Connection conn = getConnection();
		
		PreparedStatement statement = conn
				.prepareStatement("insert into id_test(id,f_from) values (?,?) ");
		statement.setLong(1, id);
		statement.setString(2, threadId);
		statement.execute();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		genId();
		/*
		 * long current=System.currentTimeMillis();
		 * System.out.println(String.format
		 * ("currentTime=%s,nextValue=%s,n2value=%s", current,current*100000,
		 * current<<4));
		 */
	}

	private static void test() {
		long currentTime = System.currentTimeMillis();
		String s = String.valueOf(currentTime);
		long max = Long.MAX_VALUE;
		String sMax = String.valueOf(max);
		System.out.println(String.format(
				"currentTime=%s,length=%s,longmax=%s,smaxLength=%s",
				currentTime, s.length(), max, sMax.length()));
	}

	private static void genId() {
		Ignite ignite = Ignition.start();
		IgniteAtomicSequence seq = ignite.atomicSequence("seqName",// 序列名
				init(), // 初始值
				true// 如果序列不存在则创建
				);
		//seq.batchSize(1);
		new Thread() {
			public void run() {
				while (true) {
					long newValue=0L;
					String threadId=null;
					try {
						long currentValue = seq.get();// 获取当前值
						newValue = seq.incrementAndGet();
						threadId = String.valueOf(Thread.currentThread().getId());
						// if (i % 10000 == 0)
						System.out.println(String.format(
								"currentValue=%s,newValue=%s,threadname=%s",
								currentValue, newValue, threadId));
					} catch (IgniteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						insertId(newValue, threadId);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						TimeUnit.MICROSECONDS.sleep(500);

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();

		System.out.println("ooook...");

	}

	private static long init2() {
		return System.currentTimeMillis() * 100000;
	}

	private static long init() {
		DateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		String r = f.format(new Date());
		r += "00000";
		System.out.println("r=" + r);
		return Long.valueOf(r);
	}

}
