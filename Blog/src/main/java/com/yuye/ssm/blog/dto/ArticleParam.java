package com.yuye.ssm.blog.dto;

import lombok.Data;

import java.util.List;

/**
 * @author yuye
 */
@Data
public class ArticleParam {

    private Integer articleId;

    private String articleTitle;

    private String articleContent;

    private Integer articleParentCategoryId;

    private Integer articleChildCategoryId;

    private Integer articleOrder;

    private Integer articleStatus;

    private String articleThumbnail;

    private List<Integer> articleTagIds;

    final private String code_begin_signal = "[code_begin]";

    final private String code_end_signal   = "[code_end]";
    String resetStrToCodeStyle(String s, int begin, int end) {
        //System.out.println("\tcode_segment {\n\t\t" + s.substring(begin, end) + "\n\t}");
        String newStr = "<pre>" + s.substring(begin + code_begin_signal.length(), end - code_end_signal.length()) + "</pre>";
        s = newStr;
        //System.out.println("\tnewStr<" + newStr +
        //                  "> \n\ts<" + s + ">");
        return s;
    }

    String haveCodeField(String s) {
        int begin_insert = 0, end_insert = 0;
        int last_bi = 0, last_ei = 0;

        while(true) {
            begin_insert = s.indexOf(code_begin_signal, last_bi);
            end_insert = s.indexOf(code_end_signal, last_ei);
            if(begin_insert == -1 || end_insert == -1) {
                //System.out.println("\t没有代码域了");
                break;
            }
            //System.out.println("\t发现一个代码域");
            end_insert = end_insert + code_end_signal.length();

            s = s.substring(0, begin_insert) + resetStrToCodeStyle(s, begin_insert, end_insert) + s.substring(end_insert);

            last_bi = begin_insert + code_begin_signal.length();
            last_ei = end_insert + code_end_signal.length();
        }
        //System.out.println("--------s: " + s);
        return s;
    }

    public void setArticleContent(String content) {
        final String signal = "test";
        String content_type = content.substring(0, 4);
        //System.out.println("content{ \n\t" + content + "\n} \n");
        //System.out.println("CodeField {\n");
        content = haveCodeField(content);
        //System.out.println(" \n} code_field_end");
//        System.out.println("content {\n" + content + "\n}");

        articleContent = content;
    }
}
