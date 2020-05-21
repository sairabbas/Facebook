import java.util.*;

public class Graph {
     Map<Model, ArrayList<Model>> map = new HashMap<>();

    public Graph(){ }

    public void addVertex(Model m){
        map.put(m, new ArrayList<>());
    }

    public void removeVertex(Model m){
        if (!this.map.containsKey(m)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }

        this.map.remove(m);

        for(Model u: this.getAllVertices()){
            this.map.get(u).remove(m);
        }
    }

    public void addEdge(Model m1, Model m2){
        if(!map.containsKey(m1))
            addVertex(m1);

        if(!map.containsKey(m2))
            addVertex(m2);

        map.get(m1).add(m2);
        map.get(m2).add(m1);
    }

    public void removeEdge(Model m1, Model m2){

        ArrayList<Model> edgeV1 = map.get(m1);
        ArrayList<Model> edgeV2 = map.get(m2);

        if(edgeV1 != null)
            edgeV1.remove(m2);

        if(edgeV2 != null)
            edgeV2.remove(m1);
    }

    public ArrayList<Model> getAdjVertices(Model p)
    {
        return map.get(p);
    }

    public int getVertexCount(){
        return map.keySet().size();
    }

    public void getEdgesCount(boolean bidirection)
    {
        int count = 0;
        for (Model v : map.keySet()) {
            count += map.get(v).size();
        }
        if (bidirection) {
            count = count / 2;
        }
        System.out.println("The graph has "
                + count
                + " edges.");
    }
/*
    public Iterable<Model> getAllVertices() { //Get all vertices in the graph
        return this.map.keySet();
    }
 */
    public Set<Model> getAllVertices() { //Get all vertices in the graph
        return this.map.keySet();
    }

    public Iterable<Model> getNeighbors(Model p){ //Get connected vertices of the vertex
        return this.map.get(p);
    }

    public boolean isConnected(Model p1, Model p2){
        return this.map.get(p1).contains(p2);
    }

    public void hasVertex(Model p)
    {
        if (map.containsKey(p)) {
            System.out.println("The graph contains "
                    + p + " as a vertex.");
        }
        else {
            System.out.println("The graph does not contain "
                    + p + " as a vertex.");
        }
    }

    public void hasEdge(Model p1, Model p2)
    {
        if (map.get(p1).contains(p2)) {
            System.out.println("The graph has an edge between "
                    + p1 + " and " + p2 + ".");
        }
        else {
            System.out.println("The graph has no edge between "
                    + p1 + " and " + p2 + ".");
        }
    }


    public Set<Model> dfs(Graph graph, Model root){
        Set<Model> visited = new LinkedHashSet<>();
        Stack<Model> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            Model vertex = stack.pop();
            if(!visited.contains(vertex)){
                visited.add(vertex);
                for(Model v: graph.getAdjVertices(vertex)){
                    stack.push(v);
                }
            }
        }
        return visited;
    }

    public Set<Model> bfs(Graph graph, Model root){
        Set<Model> visited = new LinkedHashSet<>();
        Queue<Model> queue = new LinkedList<>();

        queue.add(root);
        visited.add(root);

        while(!queue.isEmpty()){
            Model vertex = queue.poll();
            for(Model v: graph.getAdjVertices(vertex)) {
                if(!visited.contains(v)){
                    visited.add(v);
                    queue.add(v);
                }
            }
        }
        return visited;
    }


    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        for (Model v : map.keySet()) {
            builder.append(v.getName()).append(": ");
        }

        return (builder.toString());
    }



}
