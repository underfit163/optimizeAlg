package exam;

import java.util.ArrayList;
import java.util.List;

public class Task {
    //Перестановки строки
    public void reshuffleString(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        System.out.println(shuffleV2(stringBuilder));
    }

    public List<StringBuilder> shuffle(StringBuilder stringBuilder) {
        List<StringBuilder> stringBuilders = new ArrayList<>();
        if (stringBuilder.length() <= 1) {
            stringBuilders.add(stringBuilder);
            return stringBuilders;
        } else {
            for (int i = 0; i < stringBuilder.length(); i++) {
                StringBuilder stringBuilderCopy = new StringBuilder(stringBuilder);//ab
                char chSwap = stringBuilderCopy.charAt(i); //b
                stringBuilderCopy.deleteCharAt(i);//a
                addChar(stringBuilders, chSwap, shuffle(stringBuilderCopy));
            }
            return stringBuilders;
        }
    }


    public void addChar(List<StringBuilder> stringBuilders, char chSwap, List<StringBuilder> stringBuildersCopy) {
        for (int i = 0; i < stringBuildersCopy.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(chSwap);
            stringBuilder.append(stringBuildersCopy.get(i));
            stringBuilders.add(stringBuilder);
        }
    }

    public List<StringBuilder> shuffleV2(StringBuilder stringBuilder) {
        List<StringBuilder> stringBuilders = new ArrayList<>();
        if (stringBuilder.length() <= 1) {
            stringBuilders.add(stringBuilder);
            return stringBuilders;
        } else {
            StringBuilder stringBuilderCopy = new StringBuilder(stringBuilder);//ab
            char chSwap = stringBuilderCopy.charAt(stringBuilderCopy.length()-1); //b
            stringBuilderCopy.deleteCharAt(stringBuilderCopy.length()-1);//a
            addCharV2(stringBuilders, chSwap, shuffleV2(stringBuilderCopy));

            return stringBuilders;
        }
    }
    //[]  b  [a]
    public void addCharV2(List<StringBuilder> stringBuilders, char chSwap, List<StringBuilder> stringBuildersCopy) {
        for (int i = 0; i < stringBuildersCopy.size(); i++) {
            for (int j = 0; j < stringBuildersCopy.get(i).length(); j++) {
                StringBuilder stringBuilder = new StringBuilder(stringBuildersCopy.get(i));
                stringBuilder.insert(j, chSwap);
                stringBuilders.add(stringBuilder);
                //stringBuilder.deleteCharAt(j);
                if(stringBuildersCopy.get(i).length() - 1 == j) {
                    StringBuilder stringBuilderL = new StringBuilder(stringBuildersCopy.get(i));
                    stringBuilderL.append(chSwap);
                    stringBuilders.add(stringBuilderL);
                    //stringBuilderL.deleteCharAt(j);
                }
            }
        }
    }
}
