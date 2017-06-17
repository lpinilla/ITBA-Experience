package tests.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.Map;

import map.*;
import org.junit.Before;
import org.junit.Test;

public class MapTest {

    private GameMap map;
    private Position2D topLeftCorner, oneOne;
    private Tile floor, wall;

    @Before
    public void before(){
        this.map = new GameMap("main",3,3);
        this.oneOne = new Position2D(1,0);
        this.topLeftCorner = new Position2D(0,1);
        this.floor = new Tile(true, true, new CommonGround());
        this.wall = new Tile(false, false, new Wall());
        this.map.add(this.oneOne, this.floor);
        this.map.add(this.topLeftCorner, this.wall);
    }

    @Test
    public void nameTest(){
        assertEquals("main", this.map.getName());
    }

    @Test
    public void isUsingMapCollection(){
        assertTrue(this.map.getMap() instanceof Map);
    }

    @Test
    public void isNotEmpty(){
        assertTrue(this.map.getSize() != 0);
    }

    @Test
    public void isWalkable(){
        assertTrue(this.map.get(oneOne).isWalkable());
    }

    @Test
    public void isUnWalkable(){
        assertFalse(this.map.get(topLeftCorner).isWalkable());
    }

    @Test
    public void isSleepable(){
        assertTrue(this.map.get(oneOne).isSleepable());
    }

    @Test
    public void isUnSleepable(){
        assertFalse(this.map.get(topLeftCorner).isSleepable());
    }

    @Test
    public void wallTypeTest(){
        assertEquals("Wall", this.map.get(topLeftCorner).getType().getName());
    }

    @Test
    public void floorTypeTest(){
        assertEquals("CommonGround", this.map.get(oneOne).getType().getName());
    }

    @Test
    public void sizeTest(){
        assertEquals(9, this.map.getSize());
    }

    @Test
    public void getWidthTest(){
        assertEquals(3, this.map.getWidth());
    }

    @Test
    public void getHeightTest(){
        assertEquals(3, this.map.getHeight());
    }

}