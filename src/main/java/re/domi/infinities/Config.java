package re.domi.infinities;

import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config
{
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "infinities.properties");
    private static final String CONFIG_COMMENT = "Infinities config file";

    private static final String INFINITY_WITH_MENDING = "infinityWithMending";
    private static final String INFINITY_ON_CROSSBOW = "infinityOnCrossbow";
    private static final String INFINITY_WITH_MULTISHOT = "infinityWithMultishot";

    public static boolean infinityWithMending = true;
    public static boolean infinityOnCrossbow = true;
    public static boolean infinityWithMultishot = true;

    private static void read()
    {
        try
        {
            if (CONFIG_FILE.createNewFile())
            {
                write();
                return;
            }

            FileInputStream in = new FileInputStream(CONFIG_FILE);
            Properties properties = new Properties();
            properties.load(in);

            String tru = Boolean.toString(true);

            infinityWithMending = tru.equals(properties.getProperty(INFINITY_WITH_MENDING));
            infinityOnCrossbow = tru.equals(properties.getProperty(INFINITY_ON_CROSSBOW));
            infinityWithMultishot = tru.equals(properties.getProperty(INFINITY_WITH_MULTISHOT));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    static void write()
    {
        try
        {
            FileOutputStream outputStream = new FileOutputStream(CONFIG_FILE);
            Properties properties = new Properties();

            properties.setProperty(INFINITY_WITH_MENDING, Boolean.toString(infinityWithMending));
            properties.setProperty(INFINITY_ON_CROSSBOW, Boolean.toString(infinityOnCrossbow));
            properties.setProperty(INFINITY_WITH_MULTISHOT, Boolean.toString(infinityWithMultishot));

            properties.store(outputStream, CONFIG_COMMENT);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    static
    {
        read();
    }
}
