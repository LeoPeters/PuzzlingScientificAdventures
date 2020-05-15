package goldbach.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import goldbach.Goldbach;

public class GoldbachTest {
	
	@Test
	public void isPrime() {
		try {
			Class<Goldbach> gb = Goldbach.class;
			Method isPrime = gb.getDeclaredMethod("isPrime", int.class);
			isPrime.setAccessible(true);
//			Method goldbachAlgorithm = gb.getDeclaredMethod("goldbachAlgorithm", int.class);
//			Method findClosestPrime = gb.getDeclaredMethod("findClosestPrime", int.class);
//			goldbachAlgorithm.setAccessible(true);
//			findClosestPrime.setAccessible(true);
			assertEquals(true, (int) isPrime.invoke(gb.newInstance(), 11));
			assertEquals(true, (int) isPrime.invoke(4283));
			assertEquals(true, (int) isPrime.invoke(27644437));
			assertEquals(false, (int) isPrime.invoke(27644438));
			assertEquals(false, (int) isPrime.invoke(2));
			assertEquals(false, (int) isPrime.invoke(1));
			assertEquals(false, (int) isPrime.invoke(102));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
