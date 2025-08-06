package ca.mcmaster.cas.se2aa4.a3.island.urbanization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.path.GraphShortestPath;

/**
 * Builds a star road network connecting the capital to every town.
 * Towns and the capital are represented by the centroids coloured in
 * {@link Cities}. Roads are visualized by colouring the segments shared
 * by neighbouring polygons along the shortest path between two towns.
 */
public class StarNetwork {

    private final Graph graph;
    private final Structs.Mesh aMesh;
    private final Map<Integer, Integer> centroidToPolygon = new HashMap<>();
    private final List<Integer> townNodes = new ArrayList<>();
    private int capitalNode = -1;

    public StarNetwork(Structs.Mesh mesh) {
        this.aMesh = mesh;
        this.graph = new Graphable(aMesh).convert();
        indexPolygons();
        findCities();
    }

    /**
     * Creates a mapping between centroid vertex ids and polygon indices.
     */
    private void indexPolygons() {
        for (int i = 0; i < aMesh.getPolygonsCount(); i++) {
            Structs.Polygon p = aMesh.getPolygons(i);
            centroidToPolygon.put(p.getCentroidIdx(), i);
        }
    }

    /**
     * Identifies capital and towns based on the colour assigned in {@link Cities}.
     */
    private void findCities() {
        for (Structs.Polygon p : aMesh.getPolygonsList()) {
            Structs.Vertex v = aMesh.getVertices(p.getCentroidIdx());
            if (v.getPropertiesCount() > 0) {
                String color = v.getProperties(0).getValue();
                if (color.equals("0,0,0")) {
                    capitalNode = p.getCentroidIdx();
                } else if (color.equals("128,128,128")) {
                    townNodes.add(p.getCentroidIdx());
                }
            }
        }
    }

    /**
     * Colours the segments along the shortest path between the capital and all
     * towns to visualize roads.
     */
    public Structs.Mesh connectRoads() {

        if (capitalNode < 0 || townNodes.isEmpty()) {
            return aMesh; // nothing to connect
        }

        Structs.Property roadProp = Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue("0,255,255")
                .build();

        Structs.Mesh.Builder cloneMesh = Structs.Mesh.newBuilder();
        cloneMesh.addAllVertices(aMesh.getVerticesList());
        cloneMesh.addAllSegments(aMesh.getSegmentsList());
        cloneMesh.addAllPolygons(aMesh.getPolygonsList());

        for (int town : townNodes) {
            List<Node> pathNodes = new GraphShortestPath(graph)
                    .findPath(graph.getNode(capitalNode), graph.getNode(town));
            if (pathNodes == null) {
                continue;
            }

            for (int i = 0; i < pathNodes.size() - 1; i++) {
                int c1 = pathNodes.get(i).getId();
                int c2 = pathNodes.get(i + 1).getId();
                int polyIdx1 = centroidToPolygon.get(c1);
                int polyIdx2 = centroidToPolygon.get(c2);

                Structs.Polygon p1 = aMesh.getPolygons(polyIdx1);
                Structs.Polygon p2 = aMesh.getPolygons(polyIdx2);

                // find shared segment between the two polygons
                for (int segIdx : p1.getSegmentIdxsList()) {
                    if (p2.getSegmentIdxsList().contains(segIdx)) {
                        Structs.Segment.Builder sb = Structs.Segment.newBuilder(aMesh.getSegments(segIdx));
                        sb.addProperties(roadProp);
                        cloneMesh.setSegments(segIdx, sb);
                        break;
                    }
                }
            }
        }

        return cloneMesh.build();
    }
}

