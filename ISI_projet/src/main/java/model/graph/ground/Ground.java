package model.graph.ground;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Random;

/**
 * @author Alexandre
 *         06/05/2015
 */
@XmlRootElement
public class Ground {
    /**
     * logger de la classe
     */
    private static final Logger logger = LogManager.getLogger();
    /**
     * Utilitaire d'aléatoire
     */
    private static final Random random = new Random();
    /**
     * Probabilité que le terrain devienne inondé (flooded)
     * 0 par défaut
     */
    private Double chancesOfGettingFlooded;

    /**
     * Type de terrain
     */
    private GroundType type;

    public Ground(GroundType type) {
        this(type, 0.0);
    }

    public Ground(GroundType type, Double chancesOfGettingFlooded) {
        this.type = type;
        this.chancesOfGettingFlooded = chancesOfGettingFlooded;
    }

    public GroundType getType() {
        return type;
    }

    @XmlAttribute
    public void setType(GroundType type) {
        this.type = type;
    }

    public Double getChancesOfGettingFlooded() {
        return chancesOfGettingFlooded;
    }

    /**
     * Permet de définir la probabilité d'être inondé,
     * si la probabilité donnée n'est pas entre 0 et 1
     * sa valeur est rapportée à la plus proche des deux
     *
     * @param chancesOfGettingFlooded chances d'être inondé
     */
    public void setChancesOfGettingFlooded(Double chancesOfGettingFlooded) {
        if (chancesOfGettingFlooded < 0) {
            this.chancesOfGettingFlooded = 0.0;
            logger.warn(String.format("The chances of getting flooded can't be negative, has been set to 0."));
        } else if (chancesOfGettingFlooded > 1) {
            this.chancesOfGettingFlooded = 1.0;
            logger.warn(String.format("The chances of getting flooded can't be greater than 1, has been set to 1."));
        } else this.chancesOfGettingFlooded = chancesOfGettingFlooded;
    }

    /**
     * Permet d'augmenter la probabilité d'être inondé
     *
     * @param chancesOfGettingFlooded chances d'être inondé
     */
    public void increaseChancesOfGettingFlooded(Double chancesOfGettingFlooded) {
        setChancesOfGettingFlooded(getChancesOfGettingFlooded() + chancesOfGettingFlooded);
    }

    /**
     * Permet de diminuer la probabilité d'être inondé
     *
     * @param chancesOfGettingFlooded chances d'être inondé
     */
    public void decreaseChancesOfGettingFlooded(Double chancesOfGettingFlooded) {
        setChancesOfGettingFlooded(getChancesOfGettingFlooded() - chancesOfGettingFlooded);
    }

    /**
     * Essaye d'inonder le terrain
     *
     * @return vrai si le terrain est devenu inondé,
     * faux sinon.
     */
    public Boolean updateType() {
        if (chancesOfGettingFlooded == 1.0 || random.nextDouble() < chancesOfGettingFlooded) {
            type = GroundType.FLOODED;
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Ground{");
        sb.append("chancesOfGettingFlooded=").append(chancesOfGettingFlooded);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public Ground clone() {
        return new Ground(type, chancesOfGettingFlooded);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ground ground = (Ground) o;

        if (chancesOfGettingFlooded != null ? !chancesOfGettingFlooded.equals(ground.chancesOfGettingFlooded) : ground.chancesOfGettingFlooded != null)
            return false;
        return type == ground.type;

    }

    @Override
    public int hashCode() {
        int result = chancesOfGettingFlooded != null ? chancesOfGettingFlooded.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
