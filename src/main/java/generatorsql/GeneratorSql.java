package generatorsql;

public class GeneratorSql {
    public static void main(String[] args) {
        for (int i = 1; i <= 25; i++) {
            System.out.print("INSERT INTO public.option (option_text, score, question_id) VALUES ('Однозначно да', 2, " + i +");\n" +
                               "INSERT INTO public.option (option_text, score, question_id) VALUES ('Скорее да, чем нет', 1, " + i +");\n" +
                               "INSERT INTO public.option (option_text, score, question_id) VALUES ('Ни да, ни нет, нечто среднее', 0, " + i +");\n" +
                               "INSERT INTO public.option (option_text, score, question_id) VALUES ('Скорее нет, чем да', -1, " + i +");\n" +
                               "INSERT INTO public.option (option_text, score, question_id) VALUES ('Однозначно нет', -2, " + i +");\n");
        }
    }
}
