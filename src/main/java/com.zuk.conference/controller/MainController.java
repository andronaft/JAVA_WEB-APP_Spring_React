package com.zuk.conference.controller;
        import com.fasterxml.jackson.core.JsonProcessingException;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import com.zuk.conference.conection.ConnectionManager;
        import com.zuk.conference.dao.daoimpl.ConferenceDAOImpl;
        import com.zuk.conference.dao.daoimpl.ParticipantDAOImpl;
        import com.zuk.conference.model.Conference;
        import com.zuk.conference.model.Participant;
        import java.sql.*;

        import com.zuk.conference.service.impl.ConferenceServiceImpl;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.ResponseBody;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    ConnectionManager cm = new ConnectionManager();
    Connection con = cm.getConnection();

    @RequestMapping("/hello")
    @ResponseBody
    ModelAndView hello() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @RequestMapping("/about")
    @ResponseBody
    ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @RequestMapping("/conference")
    @ResponseBody
    ModelAndView conference() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @RequestMapping("/authorization")
    @ResponseBody
    ModelAndView authorization() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @RequestMapping("/getAllConference")
    String getAllConference() {
        ConferenceDAOImpl conferenceDAO = new ConferenceDAOImpl();
        return (conferenceDAO.getAllConference());
    }

    @RequestMapping("/getAccount")
    String getAccont(@RequestParam int id) {
        Participant participant = new Participant();
        participant.setId(id);
        ParticipantDAOImpl participantDAO = new ParticipantDAOImpl();

        return participantDAO.getParticipant(participant);
    }


    @RequestMapping("/register")
    String calc(@RequestParam String firstname,@RequestParam String lastname,@RequestParam Date birthday,@RequestParam String login,@RequestParam String password) throws JsonProcessingException {
        ObjectMapper objectMapper= new ObjectMapper();

        System.out.println(firstname+ lastname+birthday+login+password);

        Participant participant = new Participant(firstname,lastname,birthday,login,password);

        ParticipantDAOImpl participantDAO= new ParticipantDAOImpl();

        return (participantDAO.insertParticipant(participant));

    }

    @RequestMapping("/removeParticipantFromConf")
    String createConf(@RequestParam int id_participant , @RequestParam int conference_id , @RequestParam int admin_id , @RequestParam String admin_password){
        Participant admin = new Participant();
        admin.setId(admin_id);admin.setPassword(admin_password);
        Participant participant = new Participant();
        participant.setId(id_participant);
        Conference conference = Conference.newBuilder().setId(conference_id).build();
        System.out.println(id_participant +"id_pars"+ conference_id +"id_conf"+ admin_id +"id_admin"+ admin_password +"pass");

        ConferenceDAOImpl conferenceDAO = new ConferenceDAOImpl();

        return (conferenceDAO.removeParticipant(admin,participant,conference));
    }

    @RequestMapping("/createconf")
    String createConf(@RequestParam String name ,@RequestParam int id_room ,@RequestParam Date datee ,@RequestParam Time timee,@RequestParam int admin_id, @RequestParam String admin_password){
        Participant admin = new Participant();
        admin.setId(admin_id);admin.setPassword(admin_password);
        Conference conference = Conference.newBuilder().setName(name).setAmount_participant(0).setId_room(id_room).setDatee(datee).setTimee(timee).build();

        ConferenceDAOImpl conferenceDAO = new ConferenceDAOImpl();

        return (conferenceDAO.createConf(admin,conference));
    }

    @RequestMapping("/cancelConf")
    String cancelConf(@RequestParam int conference_id , @RequestParam int admin_id , @RequestParam String admin_password){
        Participant admin = new Participant();
        admin.setId(admin_id);admin.setPassword(admin_password);
        Conference conference = Conference.newBuilder().setId(conference_id).build();

        ConferenceDAOImpl conferenceDAO = new ConferenceDAOImpl();

        return (conferenceDAO.cancelConferece(admin,conference));
    }

    @RequestMapping("/changeConfTime")
    String cancelConf(@RequestParam int conference_id , @RequestParam int admin_id , @RequestParam String admin_password, @RequestParam Date datee, @RequestParam Time timee){
        Participant admin = new Participant();
        admin.setId(admin_id);admin.setPassword(admin_password);
        Conference conference = Conference.newBuilder().setId(conference_id).setDatee(datee).setTimee(timee).build();


        ConferenceDAOImpl conferenceDAO = new ConferenceDAOImpl();

        return (conferenceDAO.changeTime(admin,conference));
    }

    @RequestMapping("/login")
    String login(@RequestParam String login ,@RequestParam String password ){
        Participant participant = new Participant();
        participant.setLogin(login);participant.setPassword(password);

        ParticipantDAOImpl participantDAO = new ParticipantDAOImpl();

        return (participantDAO.login(participant));
    }

    @RequestMapping("/joinConference")
    String joinConference(@RequestParam int conference_id , @RequestParam int user_id ){
        ConferenceServiceImpl conferenceService = new ConferenceServiceImpl();
        return  conferenceService.addNewParticipant(user_id,conference_id);
    }

    @RequestMapping("/checkCon")
    String chekCon(){
        ConnectionManager connectionManager = new ConnectionManager();
        return String.valueOf(connectionManager.getConnection());
    }

    @RequestMapping("/findConferenceById")
    String findConferenceById(@RequestParam int conferenceId,@RequestParam int participantId){

    return "----";
    }
}
