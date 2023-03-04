module fr.tekcity.tekcity_launcher {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;
    requires javafx.base;

    requires java.prefs;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires eu.hansolo.fx.countries;
    requires eu.hansolo.fx.heatmap;
    requires eu.hansolo.toolboxfx;
    requires eu.hansolo.toolbox;
    requires com.jfoenix;
    requires MaterialFX;
    requires org.controlsfx.controls;

    requires openauth;

    requires org.jetbrains.annotations;

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.coreui;
    requires org.kordamp.ikonli.fontawesome;
    requires org.kordamp.ikonli.materialdesign;

    requires java.sql;
    requires unirest.java;
    requires com.google.gson;

    requires jbcrypt;

    opens fr.tekcity.tekcity_launcher to javafx.fxml;
    exports fr.tekcity.tekcity_launcher;
    exports fr.tekcity.tekcity_launcher.view;
    opens fr.tekcity.tekcity_launcher.view to javafx.fxml;
    exports fr.tekcity.tekcity_launcher.controller;
    opens fr.tekcity.tekcity_launcher.controller to javafx.fxml;
}