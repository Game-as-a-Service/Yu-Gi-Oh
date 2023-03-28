package tw.gaas.yugioh.data.entity.card;


import lombok.ToString;

@ToString(callSuper = true)
public class GraveYardCards extends Cards {

    public GraveYardCards() {
        limit = 99;
    }

    public void put(MonsterCard target) {
        elements.add(target);
    }
}
