package com.seattleacademy.team20;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@Controller
public class SkillController {

  private static final Logger logger = LoggerFactory.getLogger(SkillController.class);

  // @AutoWiredアノテーションでJdbcTemplateをDI, JdbcTemplateクラス型のフィールドでMySQLに接続
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @RequestMapping(value = "/skillUpload", method = RequestMethod.GET)
  public String skillUpload(Locale locale, Model model) throws IOException {
    logger.info("Welcome home! The client locale is {}.", locale);
    //		Date date = new Date();
    //		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
    //		String formattedDate = dateFormat.format(date);
    //		model.addAttribute("serverTime", formattedDate );

    initialize();
    List<Skill> skill = selectSkill();
    uploadSkill(skill);

    return "skillUpload";
  }

  //	タスク10

  // Listの宣言
  public List<Skill> selectSkill() {
    // sequel proで作ったテーブルからデータを取得する文字列をsqlという変数に入れている
    final String sql = "select * from skill";
    // jdbaTemplateでsqlを実行している
    return jdbcTemplate.query(sql, new RowMapper<Skill>() {
      public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
        // SkillCategoryの中にそれぞれのデータを入れている？そのあとRowMapper<Skills>に返却している
        return new Skill(rs.getString("category"), rs.getString("name"), rs.getInt("score"));
      }
    });
  }

  private FirebaseApp app;

  //	SDKの初期化
  public void initialize() throws IOException {
    //		Firebaseからインポートしたjsonを読み込む
    FileInputStream refreshToken = new FileInputStream(
        "/Users/user/seattle-academy20/dev-port/dev-portfolio-60b7a-firebase-adminsdk-sr16h-c5b11c16dc.json");
    FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(refreshToken))
        .setDatabaseUrl("https://dev-portfolio-60b7a.firebaseio.com/")
        .build();
    app = FirebaseApp.initializeApp(options, "others");
  }

  public void uploadSkill(List<Skill> skill) {
    //	インスタンスの定義
    final FirebaseDatabase database = FirebaseDatabase.getInstance(app);
    DatabaseReference ref = database.getReference("skills");
    //	MySQLからデータを取得する
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    Map<String, Object> map;
    skill.stream();
    Map<String, List<Skill>> skillMap = skill.stream().collect(Collectors.groupingBy(Skill::getCategory));
    for (Map.Entry<String, List<Skill>> entry : skillMap.entrySet()) {
      System.out.println(entry.getKey());
      //          System.out.println(entry.getValue());
      map = new HashMap<>();
      map.put("category", entry.getKey());
      map.put("skill", entry.getValue());
      list.add(map);
    }

    ref.setValue(list, new DatabaseReference.CompletionListener() {

      public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
        if (databaseError != null) {
          System.out.println("Data could be saved" + databaseError.getMessage());
        } else {
          System.out.println("Data save successfully.");
        }
      }
    });
  }
}
