package core.utils.url;

public class PathBuilder {
    private String path;

    public PathBuilder(String basePath) {
        this.path = basePath;
    }

    public PathBuilder addPath(String value) {
        this.path += "/" + value;
        return this;
    }

    public String build() {
        return path;
    }
}