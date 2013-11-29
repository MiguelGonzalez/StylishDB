package com.stylishdb.configuration;

import com.stylishdb.domain.MConnection;
import com.stylishdb.domain.MTab;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class AppConfiguration extends Configuration {
    public List<MConnection> conexiones;
    public List<MTab> editores;
}
