package com.matthew.plugin;

public interface Module {

    /**
     * Sets up the module.
     */
    void setUp();

    /**
     * Tears down any additional allocated resources
     */
    void teardown();

}
