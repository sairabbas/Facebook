import java.util.*;

public class Graph {
     Map<ProfileModel, List<ProfileModel>> map = new HashMap<>();

    public Graph(){ }

    public void addVertex(ProfileModel p){
        map.put(p, new LinkedList<>());
    }

    public void removeVertex(ProfileModel p){
        if (!this.map.containsKey(p)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }

        this.map.remove(p);

        for(ProfileModel u: this.getAllVertices()){
            this.map.get(u).remove(p);
        }
    }

    public void addEdge(ProfileModel p1, ProfileModel p2){
        if(!map.containsKey(p1))
            addVertex(p1);

        if(!map.containsKey(p2))
            addVertex(p2);

        map.get(p1).add(p2);
        map.get(p2).add(p1);
    }

    public void removeEdge(ProfileModel p1, ProfileModel p2){

        List<ProfileModel> edgeV1 = map.get(p1);
        List<ProfileModel> edgeV2 = map.get(p2);

        if(edgeV1 != null)
            edgeV1.remove(p2);

        if(edgeV2 != null)
            edgeV2.remove(p1);
    }

    public List<ProfileModel> getAdjVertices(ProfileModel p){
        return map.get(new ProfileModel());
    }

    public void getVertexCount(){
        System.out.println("The graph has "
                + map.keySet().size()
                + " vertex");
    }

    public void getEdgesCount(boolean bidirection)
    {
        int count = 0;
        for (ProfileModel v : map.keySet()) {
            count += map.get(v).size();
        }
        if (bidirection) {
            count = count / 2;
        }
        System.out.println("The graph has "
                + count
                + " edges.");
    }

    public Iterable<ProfileModel> getAllVertices() { //Get all vertices in the graph
        return this.map.keySet();
    }

    public Iterable<ProfileModel> getNeighbors(ProfileModel p){ //Get connected vertices of the vertex
        return this.map.get(p);
    }

    public boolean isConnected(ProfileModel p1, ProfileModel p2){
        return this.map.get(p1).contains(p2);
    }

    public void hasVertex(ProfileModel p)
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


    public void hasEdge(ProfileModel p1, ProfileModel p2)
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


    public Set<ProfileModel> dfs(Graph graph, ProfileModel root){
        Set<ProfileModel> visited = new LinkedHashSet<>();
        Stack<ProfileModel> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            ProfileModel vertex = stack.pop();
            if(!visited.contains(vertex)){
                visited.add(vertex);
                for(ProfileModel v: graph.getAdjVertices(vertex)){
                    stack.push(v);
                }
            }
        }

        return visited;
    }

    public Set<ProfileModel> bfs(Graph graph, ProfileModel root){
        Set<ProfileModel> visited = new LinkedHashSet<>();
        Queue<ProfileModel> queue = new LinkedList<>();

        queue.add(root);
        visited.add(root);

        while(!queue.isEmpty()){
            ProfileModel vertex = queue.poll();
            for(ProfileModel v: graph.getAdjVertices(vertex)) {
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

        for (ProfileModel v : map.keySet()) {
            builder.append(v.name).append(": ");
        }

        return (builder.toString());
    }


}
