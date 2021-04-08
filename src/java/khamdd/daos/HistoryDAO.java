/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamdd.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import khamdd.dtos.HistoryDTO;
import khamdd.dtos.HistoryDetailDTO;
import khamdd.utils.DBUtilities;

/**
 *
 * @author KHAM
 */
public class HistoryDAO implements Serializable {

    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    private static void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public int addHistory(HistoryDTO dto) throws Exception {
        int historyID = 0;

        try {
            String sql = "Insert into tbl_history(email, score, submittedTime, status, subjectID) values(?,?,?,?,?)";
            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getEmail());
            ps.setInt(2, dto.getScore());
            ps.setTimestamp(3, dto.getSubmittedTime());
            ps.setBoolean(4, dto.isStatus());
            ps.setInt(5, dto.getSubjectID());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                historyID = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return historyID;
    }

    public boolean addHistoryDetail(int historyID, int questionID, boolean status, String correctAnswer, String userAnswer) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into tbl_historyDetail(historyID, questionID, status, correctAnswer, userAnswer) values(?,?,?,?,?)";
            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, historyID);
            ps.setInt(2, questionID);
            ps.setBoolean(3, status);
            ps.setString(4, correctAnswer);
            ps.setString(5, userAnswer);
            check = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public ArrayList getHistory(String email) throws Exception {
        ArrayList<HistoryDTO> listHistory = null;
        try {
            String sql = "Select historyID, score, submittedTime, status, subjectID from tbl_history where email like ?";
            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            HistoryDTO dto = null;
            listHistory = new ArrayList<>();
            while (rs.next()) {
                dto = new HistoryDTO();
                dto.setEmail(email);
                dto.setHistoryID(rs.getInt("historyID"));
                dto.setScore(rs.getInt("score"));
                dto.setStatus(rs.getBoolean("status"));
                dto.setSubmittedTime(rs.getTimestamp("submittedTime"));
                dto.setSubjectID(rs.getInt("subjectID"));
                listHistory.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listHistory;
    }

    public HistoryDetailDTO getHistoryDetail(int historyID) throws Exception {
        HistoryDetailDTO dto = null;
        try {
            String sql = "Select questionID, status, correctAnswer, userAnswer from tbl_historyDetail where historyID like ?";
            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, historyID);
            rs = ps.executeQuery();
            dto = new HistoryDetailDTO();
            ArrayList<Integer> listQuestion = new ArrayList<>();
            ArrayList<Boolean> listStatus = new ArrayList<>();
            ArrayList<String> listCorrectAnswer = new ArrayList<>();
            ArrayList<String> listUserAnswer = new ArrayList<>();

            while (rs.next()) {
                listQuestion.add(rs.getInt("questionID"));
                listStatus.add(rs.getBoolean("status"));
                listCorrectAnswer.add(rs.getString("correctAnswer"));
                listUserAnswer.add(rs.getString("userAnswer"));
            }
            dto.setListQuestionID(listQuestion);
            dto.setListStatus(listStatus);
            dto.setListCorrectAnswer(listCorrectAnswer);
            dto.setListUserAnswer(listUserAnswer);
        } finally {
            closeConnection();
        }
        return dto;
    }
    public ArrayList searchHistoryByCategory(boolean status) throws Exception {
        ArrayList<HistoryDTO> listHistory = null;
        try {
            String sql = "Select historyID, score, submittedTime, status, subjectID, email from tbl_history where status like ?";
            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, status);
            rs = ps.executeQuery();
            HistoryDTO dto = null;
            listHistory = new ArrayList<>();
            while (rs.next()) {
                dto = new HistoryDTO();
                dto.setEmail(rs.getString("email"));
                dto.setHistoryID(rs.getInt("historyID"));
                dto.setScore(rs.getInt("score"));
                dto.setStatus(rs.getBoolean("status"));
                dto.setSubmittedTime(rs.getTimestamp("submittedTime"));
                dto.setSubjectID(rs.getInt("subjectID"));
                listHistory.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listHistory;
    }
    public ArrayList searchHistoryBySubject(int subjectID) throws Exception {
        ArrayList<HistoryDTO> listHistory = null;
        try {
            String sql = "Select historyID, score, submittedTime, status, subjectID, email from tbl_history where subjectID = ?";
            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, subjectID);
            rs = ps.executeQuery();
            HistoryDTO dto = null;
            listHistory = new ArrayList<>();
            while (rs.next()) {
                dto = new HistoryDTO();
                dto.setEmail(rs.getString("email"));
                dto.setHistoryID(rs.getInt("historyID"));
                dto.setScore(rs.getInt("score"));
                dto.setStatus(rs.getBoolean("status"));
                dto.setSubmittedTime(rs.getTimestamp("submittedTime"));
                dto.setSubjectID(rs.getInt("subjectID"));
                listHistory.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listHistory;
    }
}
