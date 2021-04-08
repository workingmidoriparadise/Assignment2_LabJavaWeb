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
import java.util.ArrayList;
import khamdd.dtos.QuestionDTO;
import khamdd.utils.DBUtilities;

/**
 *
 * @author KHAM
 */
public class QuestionDAO implements Serializable {

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

    public boolean createQuestion(QuestionDTO dto) throws Exception {
        boolean check = false;

        try {
            String sql = "Insert into tbl_question(questionContent, subjectID, createDate, answer1, answer2, "
                    + "answer3, answer4, correctAnswer, status) values(?,?,?,?,?,?,?,?,?)";
            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getQuestionContent());
            ps.setInt(2, dto.getSubjectID());
            ps.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
            ps.setString(4, dto.getAnswer1());
            ps.setString(5, dto.getAnswer2());
            ps.setString(6, dto.getAnswer3());
            ps.setString(7, dto.getAnswer4());
            ps.setString(8, dto.getAnswerCorrect());
            ps.setBoolean(9, dto.isStatus());
            check = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public int getSubjectIDBySubjectName(String subjectName) throws Exception {
        int subjectID = 0;
        try {
            String sql = "Select subjectID from tbl_subject where subjectName = ?";
            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, subjectName);
            rs = ps.executeQuery();
            if (rs.next()) {
                subjectID = rs.getInt("subjectID");
            }
        } finally {
            closeConnection();
        }

        return subjectID;
    }

    public ArrayList getListSubject() throws Exception {
        ArrayList<String> listSubject = null;
        try {
            String sql = "Select subjectName from tbl_subject";
            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            listSubject = new ArrayList<>();
            while (rs.next()) {
                listSubject.add(rs.getString("subjectName"));
            }
        } finally {
            closeConnection();
        }
        return listSubject;
    }

    public ArrayList searchByQuestionName(String questionName) throws Exception {
        ArrayList<QuestionDTO> listQuestion = null;

        try {
            String sql = "Select questionID, questionContent, answer1, answer2, answer3, answer4, correctAnswer, status, subjectID"
                    + " from tbl_question where questionContent like ? order by subjectID";

            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + questionName + "%");
            rs = ps.executeQuery();
            listQuestion = new ArrayList<>();
            QuestionDTO dto = null;
            while (rs.next()) {
                dto = new QuestionDTO();
                dto.setQuestionID(rs.getInt("questionID"));
                dto.setQuestionContent(rs.getString("questionContent"));
                dto.setAnswer1(rs.getString("answer1"));
                dto.setAnswer2(rs.getString("answer2"));
                dto.setAnswer3(rs.getString("answer3"));
                dto.setAnswer4(rs.getString("answer4"));
                dto.setStatus(rs.getBoolean("status"));
                dto.setSubjectID(rs.getInt("subjectID"));
                dto.setAnswerCorrect(rs.getString("correctAnswer"));
                listQuestion.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listQuestion;
    }

    public boolean updateQuestion(QuestionDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Update tbl_question set questionContent = ?, answer1 = ?,"
                    + " answer2 = ?, answer3 = ?, answer4 = ?, correctAnswer = ?"
                    + " where questionID like ?";
            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getQuestionContent());
            ps.setString(2, dto.getAnswer1());
            ps.setString(3, dto.getAnswer2());
            ps.setString(4, dto.getAnswer3());
            ps.setString(5, dto.getAnswer4());
            ps.setString(6, dto.getAnswerCorrect());
            ps.setInt(7, dto.getQuestionID());
            check = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteQuestion(int questionID) throws Exception {
        boolean check = false;

        try {
            String sql = "Update tbl_question set status = ? where questionID = ?";
            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, questionID);
            check = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean restoreQuestion(int questionID) throws Exception {
        boolean check = false;

        try {
            String sql = "Update tbl_question set status = ? where questionID = ?";
            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setInt(2, questionID);
            check = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public ArrayList searchByStatus(int status) throws Exception {
        ArrayList<QuestionDTO> listQuestion = null;
        try {
            String sql = "Select questionID, questionContent, answer1, answer2, answer3, answer4, correctAnswer, status, subjectID"
                    + " from tbl_question where status = ?  order by subjectID";
            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, status);
            rs = ps.executeQuery();
            listQuestion = new ArrayList<>();
            QuestionDTO dto = null;
            while (rs.next()) {
                dto = new QuestionDTO();
                dto.setQuestionID(rs.getInt("questionID"));
                dto.setQuestionContent(rs.getString("questionContent"));
                dto.setAnswer1(rs.getString("answer1"));
                dto.setAnswer2(rs.getString("answer2"));
                dto.setAnswer3(rs.getString("answer3"));
                dto.setAnswer4(rs.getString("answer4"));
                dto.setStatus(rs.getBoolean("status"));
                dto.setSubjectID(rs.getInt("subjectID"));
                dto.setAnswerCorrect(rs.getString("correctAnswer"));
                listQuestion.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listQuestion;
    }

    public ArrayList searchBySubject(int subjectID) throws Exception {
        ArrayList<QuestionDTO> listQuestion = null;
        try {
            String sql = "Select questionID, questionContent, answer1, answer2, answer3, answer4, correctAnswer, status, subjectID"
                    + " from tbl_question where subjectID = ?  order by subjectID";
            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, subjectID);
            rs = ps.executeQuery();
            listQuestion = new ArrayList<>();
            QuestionDTO dto = null;
            while (rs.next()) {
                dto = new QuestionDTO();
                dto.setQuestionID(rs.getInt("questionID"));
                dto.setQuestionContent(rs.getString("questionContent"));
                dto.setAnswer1(rs.getString("answer1"));
                dto.setAnswer2(rs.getString("answer2"));
                dto.setAnswer3(rs.getString("answer3"));
                dto.setAnswer4(rs.getString("answer4"));
                dto.setStatus(rs.getBoolean("status"));
                dto.setSubjectID(rs.getInt("subjectID"));
                dto.setAnswerCorrect(rs.getString("correctAnswer"));
                listQuestion.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listQuestion;
    }
    
//    public int getTotalQuest(String subjectID, String status, String questionName) throws Exception {
//        int totalQuest = 0;
//        try {
//            String sql = "Select count(questionID) as total"
//                    + " from tbl_question where subjectID = ? and status = ? and questionContent = ?";
//            conn = DBUtilities.makeConnection();
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, subjectID);
//            ps.setString(2, status);
//            ps.setString(3, "%" + questionName + "%");
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                totalQuest = rs.getInt("total");
//            }
//        } finally {
//            closeConnection();
//        }
//        return totalQuest;
//    }
    public ArrayList getQuiz(int subjectID) throws Exception {
        ArrayList<QuestionDTO> listQuestion = null;
        try {
            String sql = "Select top 5 questionID, questionContent, answer1, answer2, answer3, answer4, correctAnswer, status, subjectID"
                    + " from tbl_question where subjectID = ? and status = ? order by newid()";
            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, subjectID);
            ps.setBoolean(2, true);
            rs = ps.executeQuery();
            listQuestion = new ArrayList<>();
            QuestionDTO dto = null;
            while (rs.next()) {
                dto = new QuestionDTO();
                dto.setQuestionID(rs.getInt("questionID"));
                dto.setQuestionContent(rs.getString("questionContent"));
                dto.setAnswer1(rs.getString("answer1"));
                dto.setAnswer2(rs.getString("answer2"));
                dto.setAnswer3(rs.getString("answer3"));
                dto.setAnswer4(rs.getString("answer4"));
                dto.setStatus(rs.getBoolean("status"));
                dto.setSubjectID(rs.getInt("subjectID"));
                dto.setAnswerCorrect(rs.getString("correctAnswer"));
                listQuestion.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listQuestion;
    }
    
    public QuestionDTO getQuestionByID(int questionID) throws Exception {
        QuestionDTO dto = null;
        try {
            String sql = "Select questionContent, answer1, answer2, answer3, answer4 from tbl_question where questionID = ?";
            conn = DBUtilities.makeConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, questionID);
            rs = ps.executeQuery();
            dto = new QuestionDTO();
            if (rs.next()) {
                dto.setQuestionContent(rs.getString("questionContent"));
                dto.setAnswer1(rs.getString("answer1"));
                dto.setAnswer2(rs.getString("answer2"));
                dto.setAnswer3(rs.getString("answer3"));
                dto.setAnswer4(rs.getString("answer4"));
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
