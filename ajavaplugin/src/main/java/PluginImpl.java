import org.codeoverflow.chatoverflow.api.plugin.Plugin;

public class PluginImpl implements Plugin {
    @Override
    public void start() {
        System.out.println("I'm a java plugin!");
    }

    @Override
    public String[] getDependenciesOrWhatEver() {
        return new String[0];
    }
}
