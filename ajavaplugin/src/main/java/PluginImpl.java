import org.codeoverflow.chatoverflow.api.plugin.Plugin;
import org.codeoverflow.chatoverflow.api.plugin.configuration.Configuration;

public class PluginImpl implements Plugin {
    @Override
    public void start() {
        System.out.println("I'm a java plugin!");
    }

    @Override
    public Configuration getRequirements() {
        return null;
    }

}
