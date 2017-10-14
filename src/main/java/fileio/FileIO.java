package fileio;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import common.AABB;
import common.GameController;
import common.Vector;
import entities.world.characters.Player;
import entities.world.characters.controllers.PlayerController;
import entities.world.scenery.Box;
import physics.PhysObject;

public class FileIO {
    private static ObjectParser[] parsers = new ObjectParser[] {
            FileIO::parsePlayer,
            FileIO::parseBox
    };
    
    private interface ObjectParser {
        public void parse(Scanner s, GameController c);
    }
    
    public static void load(Scanner s, GameController c) throws IOException, ClassNotFoundException {
        while(s.hasNext()) {
            Arrays.stream(parsers).forEach(x -> x.parse(s, c));
        } 
    }
    
    private static PhysObject parsePhysObject(Scanner s) {
        if(!s.hasNext("physObject"))
            throw new Error("Expected a phys object.");
        s.next();
        AABB aabb = new AABB(new Vector(s.nextDouble(), s.nextDouble()), new Vector(s.nextDouble(), s.nextDouble()));
        return new PhysObject(aabb, s.nextDouble());
    }
    
    private static void parsePlayer(Scanner s, GameController c) {
        if(!s.hasNext("player"))
            return;
        s.next();
        PhysObject po = parsePhysObject(s);
        int health = s.nextInt();
        c.addEntity(new Player(po, new PlayerController(), health), po);
    }
    
    private static void parseBox(Scanner s, GameController c) {
        if(!s.hasNext("box"))
            return;
        s.next();
        PhysObject po = parsePhysObject(s);
        c.addEntity(new Box(po), po);
    }
}
