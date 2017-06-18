package tests.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;
import map.GameMap;
import map.MapHandler;
import map.MapNotFoundException;
import map.MapsCollectionEmptyException;
import org.junit.*;


public class MapHandlerTest {

    private MapHandler dorasMap, foxMap; //I'm the map I'm the map..
    private GameMap smallRoom, inverseSmallRoom;
    private Integer[][] smallRoomData, inverseSmallRoomData;
    private ArrayList<Integer[][]> mapList;
    private ArrayList<String> mapListNames;

    @Before
    public void before(){
        this.dorasMap = new MapHandler();
        this.foxMap = new MapHandler(); //Empty
        this.smallRoomData = inverseSmallRoomData = new Integer[3][3];
        //smallMap
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(i == 0 || j == 0 || i == 2 || j == 2){
                    this.smallRoomData[i][j] = 1; //wall
                }else{
                    this.smallRoomData[i][j] = 0; //floor
                }
            }
        }
        this.dorasMap.createParticularMap("smallRoom", 3, 3, smallRoomData);
        this.dorasMap.setCurrentMap("smallRoom");
        smallRoom = this.dorasMap.getCurrentMap();
        //inverseSmallMap
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(i == 0 || j == 0 || i == 2 || j == 2){
                    this.inverseSmallRoomData[i][j] = 0; //floor
                }else{
                    this.inverseSmallRoomData[i][j] = 1; //wall
                }
            }
        }
        this.dorasMap.createParticularMap("inverseSmallRoom", 3,
                3, inverseSmallRoomData);
        this.mapList = new ArrayList<Integer[][]>();
        this.mapListNames = new ArrayList<String>();
        this.mapList.add(smallRoomData);
        this.mapListNames.add("smallRoom");
        this.mapList.add(inverseSmallRoomData);
        this.mapListNames.add("InverseSmallRoom");
    }

    @Test
    public void currentMapTest(){
        assertEquals("smallRoom", this.dorasMap.getCurrentMap().getName());
    }

    @Test
    public void changeMapTest(){
        this.dorasMap.setCurrentMap("inverseSmallRoom"); //esto va aca? Si, está perfecto.
        assertEquals("inverseSmallRoom", this.dorasMap.getCurrentMap().getName());
    }

    @Test
    public void createMapTest(){
        this.dorasMap.createParticularMap("asd", 3, 3, inverseSmallRoomData);
        assertEquals(3, this.dorasMap.getSize());
    }

    @Deprecated //No deberíamos eliminarlo?
    @Test
    public void createMultipleMapsTest(){
        //TODO
        //check if multiple map creator works
    }

    @Test
    public void sizeTest(){
        assertEquals(2, this.dorasMap.getSize());
    }

    @Test
    public void isEmptyTest(){
        assertFalse(this.dorasMap.isEmpty());
    }

    @Test
    public void removeExistingMapTest(){
        this.dorasMap.removeMap("inverseSmallRoom");
        assertEquals(1, this.dorasMap.getSize());
    }

    @Test (expected = MapNotFoundException.class)
    public void removeNotExistingMapTest(){
        this.dorasMap.removeMap("I don't exist");
    }

    @Test (expected = MapsCollectionEmptyException.class)
    public void removeFromEmptyArrayTest(){
        this.foxMap.removeMap("Im Empty!");
    }

    @Test
    public void removeSeveralExistingMapsTest(){
        ArrayList<String> names = new ArrayList<String>();
        names.add("smallRoom");
        names.add("inverseSmallRoom");
        this.foxMap.createParticularMap("smallRoom", 3, 3, smallRoomData);
        this.foxMap.createParticularMap("inverseSmallRoom",
                3, 3, inverseSmallRoomData);
        this.foxMap.removeMaps(names);
        assertTrue(this.foxMap.isEmpty());
    }

    @Test (expected = MapNotFoundException.class)
    public void removeSeveralNotExistingMapsTest(){
        ArrayList<String> names = new ArrayList<String>();
        names.add("smallRoom");
        names.add("He exists, i don't!");
        this.foxMap.createParticularMap("smallRoom", 3, 3, smallRoomData);
        this.foxMap.createParticularMap("inverseSmallRoom",
                3, 3, inverseSmallRoomData);
        this.foxMap.removeMaps(names);
    }

    @Test (expected = MapsCollectionEmptyException.class)
    public void removeSeveralMapsFromEmptyArrayTest(){
        ArrayList<String> names = new ArrayList<String>();
        names.add("smallRoom");
        names.add("inverseSmallRoom");
        this.foxMap.removeMaps(names);
    }
}