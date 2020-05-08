import java.util.*;

public class Graph {
     Map<Database, List<Database>> map = new HashMap<>();

    public Graph(){ }

    public void addVertex(Database p){
        map.put(p, new LinkedList<>());
    }

    public void removeVertex(Database p){
        if (!this.map.containsKey(p)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }

        this.map.remove(p);

        for(Database u: this.getAllVertices()){
            this.map.get(u).remove(p);
        }
    }

    public void addEdge(Database p1, Database p2){
        if(!map.containsKey(p1))
            addVertex(p1);

        if(!map.containsKey(p2))
            addVertex(p2);

        map.get(p1).add(p2);
        map.get(p2).add(p1);
    }

    public void removeEdge(Database p1, Database p2){

        List<Database> edgeV1 = map.get(p1);
        List<Database> edgeV2 = map.get(p2);

        if(edgeV1 != null)
            edgeV1.remove(p2);

        if(edgeV2 != null)
            edgeV2.remove(p1);
    }

    public List<Database> getAdjVertices(Database p){
        return map.get(new Database());
    }

    public void getVertexCount(){
        System.out.println("The graph has "
                + map.keySet().size()
                + " vertex");
    }

    public void getEdgesCount(boolean bidirection)
    {
        int count = 0;
        for (Database v : map.keySet()) {
            count += map.get(v).size();
        }
        if (bidirection) {
            count = count / 2;
        }
        System.out.println("The graph has "
                + count
                + " edges.");
    }

    public Iterable<Database> getAllVertices() { //Get all vertices in the graph
        return this.map.keySet();
    }

    public Iterable<Database> getNeighbors(Database p){ //Get connected vertices of the vertex
        return this.map.get(p);
    }

    public boolean isConnected(Database p1, Database p2){
        return this.map.get(p1).contains(p2);
    }

    public void hasVertex(Database p)
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

    public void hasEdge(Database p1, Database p2)
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


    public Set<Database> dfs(Graph graph, Database root){
        Set<Database> visited = new LinkedHashSet<>();
        Stack<Database> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            Database vertex = stack.pop();
            if(!visited.contains(vertex)){
                visited.add(vertex);
                for(Database v: graph.getAdjVertices(vertex)){
                    stack.push(v);
                }
            }
        }
        return visited;
    }

    public Set<Database> bfs(Graph graph, Database root){
        Set<Database> visited = new LinkedHashSet<>();
        Queue<Database> queue = new LinkedList<>();

        queue.add(root);
        visited.add(root);

        while(!queue.isEmpty()){
            Database vertex = queue.poll();
            for(Database v: graph.getAdjVertices(vertex)) {
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

        for (Database v : map.keySet()) {
            builder.append(v.name).append(": ");
        }

        return (builder.toString());
    }


}
