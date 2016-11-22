package resources;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Vamparam on 22.11.2016.
 */

public class Resource {
    private static final String DEFAULT_PROPERTY_NAME = "resources.text";
    private static String basename = null;
    private static ResourceBundle resources = null;

    private Resource() {
    }

    public static void createResourcer() {
        Resource.createResourcer(null);
    }

    public static void setBasename(String basename) {
        if (basename == null) {
            if (Resource.basename == null) {
                Resource.basename = Resource.DEFAULT_PROPERTY_NAME;
            }
        } else {
            Resource.basename = basename;
        }
    }

    public static void createResourcer(String basename) {
        if (Resource.resources == null) {
            Resource.setBasename(basename);
            Resource.resources = ResourceBundle.getBundle(Resource.basename,
                    Locale.getDefault());
        } else {
            Locale systemLocale = Locale.getDefault();
            Locale resourcerLocale = Resource.resources.getLocale();
            if (!(resourcerLocale.equals(systemLocale))) {
                Resource.resources = ResourceBundle.getBundle(
                        Resource.basename, systemLocale);
            }
        }
    }

    public static String getString(String parameter) {
        Resource.createResourcer();
        return Resource.resources.getString(parameter);
    }

    public static int getInt(String parameter) {
        return Integer.parseInt(getString(parameter));
    }

    public static byte getByte(String parameter) {
        return Byte.parseByte(getString(parameter));
    }
}
