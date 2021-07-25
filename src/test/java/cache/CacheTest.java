package cache;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

public class CacheTest {
    @Test
    public void whenAdd() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 2);
        Base base3 = new Base(3, 3);
        cache.add(base1);
        cache.add(base2);
        cache.add(base3);
        assertThat(cache.get(base1.getId()), is(base1));
        assertThat(cache.get(base2.getId()), is(base2));
        assertThat(cache.get(base3.getId()), is(base3));
    }

    @Test
    public void whenUpdate() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        cache.add(base1);
        Base expected = new Base(1, 1);
        expected.setName("expected");
        cache.update(expected);
        assertThat(cache.get(base1.getId()).getName(), is(expected.getName()));
    }

    @Test
    public void whenDelete() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        cache.add(base1);
        cache.delete(base1);
        assertThat(cache.get(base1.getId()), nullValue());
    }

    @Test(expected = OptimisticException.class)
    public void whenNotTheSameVersion() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        Base base2 = new Base(1, 2);
        cache.add(base1);
        cache.update(base2);
    }
}