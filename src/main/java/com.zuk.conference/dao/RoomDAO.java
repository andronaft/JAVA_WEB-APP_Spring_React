package com.zuk.conference.dao;

import com.zuk.conference.model.Room;

public interface RoomDAO {
    void insertRoom();
    Room getRoomFromId(int id);
    Room getRoom();
}
