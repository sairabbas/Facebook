import java.util.*;

public class Graph {
     Map<Model.Profile, List<Model.Profile>> map = new HashMap<>();

    public Graph(){ }

    public void addVertex(Model.Profile m){
        map.put(m, new LinkedList<>());
    }

    public void removeVertex(Model.Profile m){
        if (!this.map.containsKey(m)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }

        this.map.remove(m);

        for(Model.Profile u: this.getAllVertices()){
            this.map.get(u).remove(m);
        }
    }

    public void addEdge(Model.Profile m1, Model.Profile m2){
        if(!map.containsKey(m1))
            addVertex(m1);

        if(!map.containsKey(m2))
            addVertex(m2);

        map.get(m1).add(m2);
        map.get(m2).add(m1);
    }

    public void removeEdge(Model.Profile m1, Model.Profile m2){

        List<Model.Profile> edgeV1 = map.get(m1);
        List<Model.Profile> edgeV2 = map.get(m2);

        if(edgeV1 != null)
            edgeV1.remove(m2);

        if(edgeV2 != null)
            edgeV2.remove(m1);
    }

    public List<Model.Profile> getAdjVertices(Model.Profile p)
    {
        return map.get(new Model());
    }

    public int getVertexCount(){
        return map.keySet().size();

    }

    public void getEdgesCount(boolean bidirection)
    {
        int count = 0;
        for (Model.Profile v : map.keySet()) {
            count += map.get(v).size();
        }
        if (bidirection) {
            count = count / 2;
        }
        System.out.println("The graph has "
                + count
                + " edges.");
    }

    public Iterable<Model.Profile> getAllVertices() { //Get all vertices in the graph
        return this.map.keySet();
    }

    public Iterable<Model.Profile> getNeighbors(Model p){ //Get connected vertices of the vertex
        return this.map.get(p);
    }

    public boolean isConnected(Model.Profile p1, Model.Profile p2){
        return this.map.get(p1).contains(p2);
    }

    public void hasVertex(Model.Profile p)
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

    public void hasEdge(Model.Profile p1, Model.Profile p2)
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


    public Set<Model.Profile> dfs(Graph graph, Model.Profile root){
        Set<Model.Profile> visited = new LinkedHashSet<>();
        Stack<Model.Profile> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            Model.Profile vertex = stack.pop();
            if(!visited.contains(vertex)){
                visited.add(vertex);
                for(Model.Profile v: graph.getAdjVertices(vertex)){
                    stack.push(v);
                }
            }
        }
        return visited;
    }

    public Set<Model.Profile> bfs(Graph graph, Model.Profile root){
        Set<Model.Profile> visited = new LinkedHashSet<>();
        Queue<Model.Profile> queue = new LinkedList<>();

        queue.add(root);
        visited.add(root);

        while(!queue.isEmpty()){
            Model.Profile vertex = queue.poll();
            for(Model.Profile v: graph.getAdjVertices(vertex)) {
                if(!visited.contains(v)){
                    visited.add(v);
                    queue.add(v);
                }
            }
        }
        return visited;
    }

    /*
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        for (Model v : map.keySet()) {
            builder.append(v.name).append(": ");
        }

        return (builder.toString());
    }
     */


}
