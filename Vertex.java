import java.util.Objects;

public class Vertex {
    ProfileModel model;

    public Vertex(ProfileModel model){
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex vertex = (Vertex) o;
        return model.equals(vertex.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }
}
