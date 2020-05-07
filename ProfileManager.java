import java.util.Observable;

public class ProfileManager extends java.util.Observable {
    //Holds all of profiles
    private Graph allProfiles;

    public ProfileManager(){
        allProfiles = new Graph();
    }

    public void addProfile(ProfileModel p){
        allProfiles.addVertex(p);
    }

    /**
     * Removes all edges connected to vertex and then removes vertex
     * @param p the vertex
     * */
    public void removeProfile(ProfileModel p){
        for(ProfileModel u: allProfiles.getAdjVertices(p)){
            this.allProfiles.removeEdge(p, u);
        }

        this.allProfiles.removeVertex(p);

    }

    public ProfileModel searchProfile(String name){
        ProfileModel v = new ProfileModel();

        for(ProfileModel u : allProfiles.getAllVertices()){
            if(u.getName().equals(name))
                 return u.getProfile(u);
        }
        return v;
    }

    public void createFriendship(ProfileModel user, ProfileModel friend) {
        allProfiles.addEdge(user, friend);
    }


    /** Displays each profile's information and friends. */
    public Iterable<ProfileModel> getFriendList(ProfileModel startPoint) {
        return this.allProfiles.getNeighbors(startPoint);
    }
}