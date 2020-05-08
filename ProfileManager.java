import java.util.ArrayList;

public class ProfileManager{
    //Holds all of profiles
    private Graph allProfiles;

    public ProfileManager(){
        allProfiles = new Graph();
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

    public Database searchProfile(String name){
        Database v = new Database();

        for(Database u : allProfiles.getAllVertices()){
            if(u.getName().equals(name))
                 return u.getProfile(u);
        }
        return v;
    }

    public void createFriendship(Database user, Database friend) {
        allProfiles.addEdge(user, friend);
    }


    /** Displays each profile's information and friends. */
    public Iterable<Database> getFriendList(Database startPoint) {
        return this.allProfiles.getNeighbors(startPoint);
    }

}