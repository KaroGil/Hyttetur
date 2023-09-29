package inf112.skeleton.app.projectile;

import inf112.skeleton.projectile.Effect;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EffectTest {

    private static Effect mockEffect;

    @BeforeAll
    static void setUp() {
        mockEffect = new Effect(10,11, "effect1");
    }

    @Test
    void testConstructor() {

        assertEquals("effect1", mockEffect.getName());
        assertEquals(10, mockEffect.getDuration());
        assertEquals(11, mockEffect.getValue());
        assertTrue(mockEffect.isActive());
    }

    @Test
    void testPOISION() {
        Effect actualPOISONResult = Effect.POISON(1, 2);
        assertEquals("POISON", actualPOISONResult.getName());
        assertEquals(1, actualPOISONResult.getDuration());
        assertEquals(2, actualPOISONResult.getValue());
    }


    @Test
    void getNameTest(){
        assertEquals("effect1", mockEffect.getName());
    }
}
