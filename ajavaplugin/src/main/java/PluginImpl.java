import org.codeoverflow.chatoverflow.api.plugin.Plugin;
import org.codeoverflow.chatoverflow.api.plugin.configuration.Requirements;

public class PluginImpl implements Plugin {
    @Override
    public void start() {
        System.out.println("I'm a java plugin!");
    }

    @Override
    public Requirements getRequirements() {
        return null;
    }

}
