import org.codeoverflow.chatoverflow.api.plugin.Pluggable;
import org.codeoverflow.chatoverflow.api.plugin.Plugin;
import org.codeoverflow.chatoverflow.api.plugin.PluginManager;

public class PluggableImpl implements Pluggable {
    @Override
    public String getName() {
        return "A Java plugin";
    }

    @Override
    public String getAuthor() {
        return "sebinside";
    }

    @Override
    public String getDescription() {
        return "I would not recommend it, but yes, it is possible to write plugins in java.";
    }

    @Override
    public int getMajorAPIVersion() {
        return 1;
    }

    @Override
    public int getMinorAPIVersion() {
        return 0;
    }

    @Override
    public Plugin createNewPluginInstance(PluginManager manager) {
        return new PluginImpl();
    }
}
