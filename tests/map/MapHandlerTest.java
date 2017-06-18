package tests.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;
import map.GameMap;
import map.MapHandler;
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
        this.foxMap = new MapHandler(); //to test multiple creation of maps
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


        //this.foxMap.createMaps(this.mapList, this.mapListNames);
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
    public void eraseMapTest(){
        this.dorasMap.removeMap("inverseSmallRoom");
        assertEquals(1, this.dorasMap.getSize());
    }

    @Test
    public void eraseSeveralMapsTest(){
        ArrayList<String> names = new ArrayList<String>();
        names.add("smallRoom");
        names.add("inverseSmallRoom");
        this.foxMap.createParticularMap("smallRoom", 3, 3, smallRoomData);
        this.foxMap.createParticularMap("inverseSmallRoom",
                3, 3, inverseSmallRoomData);
        this.foxMap.removeMaps(names);
        assertTrue(this.foxMap.isEmpty());
    }
}