import java.util.ArrayList;

public class ProfileManager{
    //Holds all of profiles
    private Graph allProfiles;
    private ArrayList<Database> friendList;

    public ProfileManager(){
        allProfiles = new Graph();
        friendList = new ArrayList<>();
    }

    public void addProfile(Database p){
        allProfiles.addVertex(p);
    }

    /**
     * Removes all edges connected to vertex and then removes vertex
     * @param p the vertex
     * */
    public void removeProfile(Database p){
        for(Database u: allProfiles.getAdjVertices(p)){
            this.allProfiles.removeEdge(p, u);
        }

        this.allProfiles.removeVertex(p);

    }

    public int getProfilesCount(){
        return allProfiles.getVertexCount();
    }

    public Database searchProfile(String name){
        Database v = new Database();

        for(Database u : allProfiles.getAllVertices()){
            if(u.getName().equals(name))
                System.out.println("User " + name +" found!");
                 return u.getProfile(u);
        }
        System.out.println("User not found in Database Manager.");
        return v;
    }

    public void createFriendship(Database user, Database friend) {
        allProfiles.addEdge(user, friend);
    }


    /** Displays each profile's information and friends. */
    /*
    public Iterable<Database> getFriendList(Database startPoint) {
        return this.allProfiles.getNeighbors(startPoint);
    }

     */
    public ArrayList<Database> getFriendList(Database startPoint) {
        friendList = (ArrayList<Database>) this.allProfiles.getAdjVertices(startPoint);
        return friendList;
    }

}