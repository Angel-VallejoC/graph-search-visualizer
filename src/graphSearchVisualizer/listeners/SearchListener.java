package graphSearchVisualizer.listeners;

public interface SearchListener {
    void started();
    void finished(String method, boolean result);
}
