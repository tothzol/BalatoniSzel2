package hu.unipannon.mik.balatoniszel.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.io.File;

public class Apartman implements Serializable {
    Logger LOG = LoggerFactory.getLogger(Apartman.class);
    private static final long serialVersionUID = -5771420064864866043L;

    private Map<String, Szoba> Szobak;
    private Map<String, Vendeg> Vendegek;
    private ConcurrentHashMap<String, Foglalas> Foglalasok;


    public Apartman(ReservationRepository reservationRepository, RoomRepository roomRepository) {

    }


}
