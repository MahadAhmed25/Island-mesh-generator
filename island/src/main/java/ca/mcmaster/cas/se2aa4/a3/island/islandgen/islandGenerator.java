package ca.mcmaster.cas.se2aa4.a3.island.islandgen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.shape.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.shape.Square;
import ca.mcmaster.cas.se2aa4.a3.island.shape.TiltedOval;
import ca.mcmaster.cas.se2aa4.a3.island.urbanization.Cities;
import ca.mcmaster.cas.se2aa4.a3.island.urbanization.StarNetwork;
import ca.mcmaster.cas.se2aa4.a3.island.shape.Shape;
import ca.mcmaster.cas.se2aa4.a3.island.Lagoon2.Lagoon2;
import ca.mcmaster.cas.se2aa4.a3.island.aquifers.Aquifers;
import ca.mcmaster.cas.se2aa4.a3.island.beaches.Beaches;
import ca.mcmaster.cas.se2aa4.a3.island.biomes.Biomes;
import ca.mcmaster.cas.se2aa4.a3.island.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a3.island.elevation.*;
import ca.mcmaster.cas.se2aa4.a3.island.lakes.Lakes;
import ca.mcmaster.cas.se2aa4.a3.island.lakes.Rivers;

public class islandGenerator {
    private Structs.Mesh aMesh;
    private Configuration config;
    public static String biomeArg;

    public islandGenerator(Configuration config) throws IOException {
        this.aMesh = new MeshFactory().read(config.input());
        this.config = config;

    }

    public Structs.Mesh generate() throws IOException {
        biomeArg = (config.biomes());
        Structs.Mesh mesh = registerShape();

        for (UnaryOperator<Structs.Mesh> step : buildPipeline()) {
            mesh = step.apply(mesh);
        }

        // Seed generation
        SeedGen seedGen = new SeedGen();
        long seed = (config.seed() == null) ? (System.currentTimeMillis()) : Long.parseLong(config.seed());
        if (config.seed() == null)
            seedGen.saveMesh(seed, mesh);
        mesh = seedGen.getMesh(seed);
        System.out.println("SEED: " + seed);

        return mesh; // returns the mesh
    }

    private List<UnaryOperator<Structs.Mesh>> buildPipeline() {
        List<UnaryOperator<Structs.Mesh>> steps = new ArrayList<>();

        if (config.mode() != null && config.mode().equals("lagoon")) {
            steps.add(m -> new Lagoon2(m).build());
        }

        steps.add(m -> new Aquifers(m).enrichAquifers(config.aquifer()));
        steps.add(m -> new Lakes(m).generateLakes(Integer.parseInt(config.lakes())));
        steps.add(m -> new Rivers(m).generateRivers(Integer.parseInt(config.rivers())));
        steps.add(this::registerElevation);
        steps.add(m -> new Beaches(m).enrichBeaches());
        steps.add(m -> new Cities(Integer.parseInt(config.cities()), m).addCapital());
        steps.add(m -> new StarNetwork(m).connectRoads());
        steps.add(new Plains()::addElevation);
        steps.add(m -> new Temp(m).enrichTemp()); // index 4
        steps.add(m -> new Biomes(m).enrichBiomes());

        return steps;
    }

    public Structs.Mesh registerElevation(Structs.Mesh mesh){
        if(config.elevation() == null)
            return mesh;
        
        switch(config.elevation()){
            case "volcano":
                mesh = new Volcano().build(mesh);
                break;
            case "rockymountain":
                mesh = new RockMountain().build(mesh);
                break;
            case "both":
                mesh = new Volcano().build(mesh);
                mesh = new RockMountain().build(mesh);
                break;

            default:
                throw new IllegalArgumentException("Specified Elevation type does not exist -- " + config.elevation());
        }

        return mesh;
    }

    public Structs.Mesh registerShape(){
        Shape iMesh;
        MeshDimension dim = new MeshDimension(aMesh);

        //default shape if none is provided
        if(config.shape() == null)
            return new Circle(dim.maxX, dim.maxY).build(aMesh);

        switch(config.shape()){
            case "square":
                iMesh = new Square(dim.maxX, dim.maxY);
                break;
            case "circle":
                iMesh = new Circle(dim.maxX, dim.maxY);
                break;
            case "oval":
                iMesh = new TiltedOval(dim.maxX, dim.maxY);
                break;
            default:
                throw new IllegalArgumentException("Unknown shape: " + config.shape());
        }
        return iMesh.build(aMesh);
    }
}