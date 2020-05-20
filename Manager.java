import java.util.ArrayList;

/**Class holds a graph full of Model objects.
 * Able to add/remove Model obj to Graph tree.
 * Has getter methods for Graph tree.
 * Able to create vertex/edges for tree.*/

public class Manager {
    //Holds all of profiles
    private Graph users;
    private ArrayList<Model> friends;

    public Manager(){
        users = new Graph();
        friends = new ArrayList<>();
    }

    public void addProfile(Model m){
        users.addVertex(m);
    }

    /**
     * Removes all edges connected to vertex and then removes vertex
     * @param p the vertex
     * */
    public void removeProfile(Model p){
        for(Model u: users.getAdjVertices(p)){
            this.users.removeEdge(p, u);
        }

        this.users.removeVertex(p);
    }

    public int getProfilesCount(){
        System.out.println("Total Users in Graph: " + users.getVertexCount());
        return users.getVertexCount();
    }

    public Model searchProfile(String name){
        Model v = new Model();

        for(Model u : users.getAllVertices()){
            if(u.getName().equals(name)){
                System.out.println("User " + name +" found!");
                System.out.println("Status: " + u.getStatus());
                return u;
            }
        }
        System.out.println("User not found in Database Manager.");
        return v;
    }

    public void getEdges(boolean bidirectional){
        users.getEdgesCount(bidirectional);
    }

    public void createFriendship(Model user, Model friend) {
        users.addEdge(user, friend);
    }


    /** Displays each profile's information and friends. */
    /*
    public Iterable<Database> getFriendList(Database startPoint) {
        return this.allProfiles.getNeighbors(startPoint);
    }

     */
    public ArrayList<Model> getFriendList(Model startPoint) {
        friends = this.users.getAdjVertices(startPoint);
        return friends;
    }

}