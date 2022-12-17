package menu.domain;

import java.util.ArrayList;
import java.util.List;

public class Coach {

    private final static int NAME_MAX_LENGTH = 4;
    private final static int NAME_MIN_LENGTH = 2;

    private final String name;
    private final List<Menu> hateMenus;
    private final List<Menu> lunchMenus = new ArrayList<>();

    public Coach(String name,
                 List<Menu> hateMenus) {
        validateName(name);
        validateHateMenus(hateMenus);
        this.name = name;
        this.hateMenus = new ArrayList<>(hateMenus);
    }

    private void validateHateMenus(List<Menu> hateMenus) {
        if (hateMenus.size() > 2) {
            throw new IllegalArgumentException("코치가 못먹는 메뉴는 최대 2개입니다. 추가할려는 메뉴 갯수  : " +
                    hateMenus.size());
        }

        if (hateMenus.stream().distinct().count() != hateMenus.size()) {
            throw new IllegalArgumentException("중복된 메뉴가 존재합니다.");
        }
    }

    private void validateName(String name) {
        if (name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException("코치의 이름은 최소 2글자, 최대 4글자입니다. 입력값 : " + name);
        }
    }


    public void addLunchMenu(Category category) {
        while (true) {
            Menu randomMenuByCategory = Menu.createRandomMenuByCategory(category);
            if (!isHateOrDuplicateMenu(randomMenuByCategory)) {
                lunchMenus.add(randomMenuByCategory);
                break;
            }
        }
    }

    private boolean isHateOrDuplicateMenu(Menu menu) {
        return isHateMenu(menu) || duplicateLunchMenu(menu);
    }

    private boolean isHateMenu(Menu menu) {
        return hateMenus.contains(menu);
    }

    private boolean duplicateLunchMenu(Menu menu) {
        return lunchMenus.contains(menu);
    }


    public List<Menu> getLunchMenus() {
        return lunchMenus;
    }

    public String getName() {
        return name;
    }
}
