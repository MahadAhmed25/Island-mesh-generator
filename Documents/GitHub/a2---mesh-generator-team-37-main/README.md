# Assignment A2: Mesh Generator

-   Mahad Ahmed [ahmem73@mcmaster.ca]
-   Saad Salman [salmam12@mcmaster.ca]
-   Ali Zia [ziam8@mcmaster.ca]

## Installation instructions

```
mosser@azrael A2 % mvn install
```

It creates two jars:

`generator/generator.jar` to generate meshes  
`visualizer/visualizer.jar` to visualize such meshes as SVG files

## Examples of Execution

### Generating a mesh, grid or irregular

```
mosser@azrael A2 % java -jar generator/generator.jar -k grid -h 1080 -w 2000 -p 2000 -s 20 -o img/grid.mesh
mosser@azrael A2 % java -jar generator/generator.jar -k irregular -h 2000 -w 2000 -p 1000 -s 20 -o img/irregular.mesh
```

One can run the generator with `-help` as option to see the different command line arguments that are available

## Visualizing a mesh, (regular or debug)

```
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/grid.mesh -o img/grid.svg
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/grid.mesh -o img/grid_debug.svg -x
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular.svg
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular_debug.svg -x
```

Note: PDF versions of the SVG files were created with `rsvg-convert`

## Generating the Island

Run island generator using a mesh named `irregular.mesh` as input and producing a mesh named `output.mesh` as output
Inputs:

1. --mode lagoon generates a lagoon, to not create a lagoon, exclude the --mode argument
2. --shape arguments include circle, shape, oval
3. --lakes argument is number of lakes you want to create
4. --aquifers argument is number of aquifers you want to create
5. --biomes: arguments include grassland and desert
6. --seed argument can be included to generate a previous mesh
7. --elevation argument include volcano/rockymountain/both

```
java -jar island/island.jar -i img/irregular.mesh -o img/output.mesh --mode lagoon --shape circle --lakes 10 --rivers 10 --aquifers 20 --biomes grassland --elevation volcano
```

## Visualizing the Island

```
java -jar visualizer/visualizer.jar -i img/output.mesh -o img/output.svg
```

## Backlog

### Product Backlog

| Id  | Feature title                                                   | Who?         | Start    | End      | Status |
| :-: | --------------------------------------------------------------- | ------------ | -------- | -------- | ------ |
| F01 | Creating a minimal mesh ADT                                     | Mahad & Saad | 7/02/23  | 20/02/23 | D      |
| F02 | Producing full meshes                                           | Saad & Mahad | 11/02/23 | 22/02/23 | D      |
| F03 | Playing with rendering (Setting colors/thicknesses)             | Ali          | 11/02/23 | 21/02/23 | D      |
| F04 | Visualization mode                                              | Ali          | 15/02/23 | 23/02/23 | D      |
| F05 | Generate Random Points                                          | Saad & Ali   | 15/02/23 | 24/02/23 | D      |
| F06 | Compute the Voronoi Diagram                                     | Ali          | 18/02/23 | 27/02/23 | D      |
| F07 | Crop the mesh to the expected size                              | Mahad        | 20/02/23 | 27/02/23 | D      |
| F08 | Apply Lloyd relaxation                                          | Mahad        | 22/02/23 | 27/02/23 | D      |
| F09 | Compute neiborhood relationships using Delaunay’s triangulation | Mosser       |          |          |        |
| F10 | For each irregular polygon, reorder its segments                | Mosser       |          |          |        |
| A3  | -------------------------------------------------               | ---------    | -------- | ------   | A3     |
| F11 | Tile Creation Water and Land (Differentiation)                  | Mahad        | 1/03/23  | 7/3/23   | D      |
| F12 | Centering Function (Creating Shapes figure)                     | Ali          | 7/03/23  | 14/03/23 | D      |
| F13 | Lagoons (Finishing MVP)                                         | Mahad        | 15/03/23 | 19/03/23 | D      |
| F14 | Shapes (Circle, Square, Oval)                                   | Ali          | 15/03/23 | 21/03/23 | D      |
| F15 | Elevation                                                       | Mahad        | 15/03/23 | 26/03/23 | D      |
| F16 | Lakes                                                           | Ali          | 15/03/23 | 23/03/23 | D      |
| F17 | Rivers                                                          | Mahad        | 15/03/23 | 26/03/23 | D      |
| F19 | Aquifers (Dark bodies of water)                                 | Saad         | 15/03/23 | 21/03/23 | D      |
| F20 | Soil Absorbtion                                                 |              | 15/03/23 |          |        |
| F21 | Temperature                                                     | Saad         | 17/03/23 | 26/03/23 | D      |
| F22 | Biomes                                                          | Saad         | 15/03/23 | 26/03/23 | D      |
| F23 | Seed (Reproduction)                                             | Ali          | 15/03/23 | 26/03/23 | D      |
| F24 | Whittaker Diagrams                                              | Ali          | 15/03/23 | 26/03/23 | D      |
