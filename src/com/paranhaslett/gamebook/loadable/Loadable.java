package com.paranhaslett.gamebook.loadable;

import com.paranhaslett.gamebook.loader.EmaLoader;
import com.paranhaslett.gamebook.loader.Loader;
import com.paranhaslett.gamebook.loader.XMLLoader;
import com.paranhaslett.gamebook.model.Item;

public interface Loadable {
    XMLLoader xmlLoader = new XMLLoader();
    EmaLoader emaLoader = new EmaLoader();

    void load(Loader ff, Item item);

    void save(Loader ff, Item item);


}
