package com.seattleacademy.team20;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Handles requests for the application home page.
 */
 @Controller
public class dbAccessSampleController {
    private static final Logger logger = LoggerFactory.getLogger(dbAccessSampleController.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * 検索処理サンプルAPI
     *
     * @param locale ロケール情報
     * @param model モデル情報
     * @return 遷移先画面名
     */
    @RequestMapping(value = "/dbAccessSample", method = RequestMethod.GET)
    public String dbAccessSample(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);
        // JSPに渡すデータを設定する
        model.addAttribute("items", setSelectList());
        return "dbAccessSample";
    }
    /**
     * 登録処理サンプルメソッド
     *
     * @param locale ロケール情報
     * @param model モデル情報
     * @return 遷移先画面名
     */
    @Transactional
    @RequestMapping(value = "/insertSample", method = RequestMethod.POST)
    public String insertSample(Locale locale, Model model) {
        logger.info("Welcome insret! The client locale is {}.", locale);
        // 本当は画面からパラメータを受け取って登録したい気分・・・
        jdbcTemplate.update("INSERT INTO prefectures (id, name, capital_city) VALUES (1000, \"ハワイ\", \"ホノルル\")");
        model.addAttribute("resultMessage", "登録完了");
        model.addAttribute("items", setSelectList());
        return "dbAccessSample";
    }
    /**
     * 更新処理サンプルメソッド
     *
     * @param locale ロケール情報
     * @param model モデル情報
     * @return 遷移先画面名
     */
    @Transactional
    @RequestMapping(value = "/updateSample", method = RequestMethod.POST)
    public String updateSample(Locale locale, Model model) {
        logger.info("Welcome update! The client locale is {}.", locale);
        // 本当は画面からパラメータを受け取って登録したい気分・・・
        jdbcTemplate.update("UPDATE prefectures SET name = \"字が汚くて読めない\", capital_city = \"ちゃんと書け\" WHERE id = 1000");
        model.addAttribute("resultMessage", "更新完了");
        model.addAttribute("items", setSelectList());
        return "dbAccessSample";
    }
    /**
     * 削除処理サンプルメソッド
     *
     * @param locale ロケール情報
     * @param model モデル情報
     * @return 遷移先画面名
     */
    @Transactional
    @RequestMapping(value = "/deleteSample", method = RequestMethod.POST)
    public String deleteSample(Locale locale, Model model) {
        logger.info("Welcome delete! The client locale is {}.", locale);
        // 本当は画面からパラメータを受け取って登録したい気分・・・
        jdbcTemplate.update("DELETE FROM prefectures WHERE id = 1000");
        model.addAttribute("resultMessage", "削除完了");
        model.addAttribute("items", setSelectList());
        return "dbAccessSample";
    }
    /**
     * 検索処理サンプルメソッド
     * ※あくまでもサンプルです。ほかにもまだまだ書き方あります
     * ※更新処理の後も再描画のために最新情報を取得する必要があるため検索処理を外だしする
     */
    private List<Map<String, String>> setSelectList() {
        // JSPに渡すデータを設定する
        List<Map<String, String>> list = jdbcTemplate.query("select name, capital_city from prefectures",
                new RowMapper<Map<String, String>>() {
                    @SuppressWarnings({ "rawtypes", "unchecked" })
                    public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Map<String, String> map = new HashMap();
                        map.put("name", rs.getString("name"));
                        map.put("capital_city", rs.getString("capital_city"));
                        return map;
                    }
                });
        return list;
    }
}
