package edu.put.ma.rna_aligner;

import java.util.ArrayList;

public class Nucleotide {
  public ArrayList<Coordinates> representatives;
  private String code;

  private String key;

  private String chain;
  private String number;

  public Nucleotide() {
    representatives = new ArrayList<Coordinates>();
  }

  public Nucleotide(
      final ArrayList<Coordinates> _representatives, final String code, final String key,
      final String chain, final String number) {
    representatives = _representatives;
    this.code = code;
    this.key = key;
    this.chain = chain;
    this.number = number;
  }

  @Override
  public Object clone() {
    Nucleotide clone = new Nucleotide();
    for (final Coordinates rep : this.representatives) {
      clone.representatives.add(rep.clone());
    }
    return clone;
  }

  public static ArrayList<Coordinates> NucleotidesToList(final ArrayList<Nucleotide> nucleotides) {
    ArrayList<Coordinates> result = new ArrayList<Coordinates>();
    for (Nucleotide nucleotide : nucleotides) {
      for (final Coordinates rep : nucleotide.representatives) {
        result.add(rep);
      }
    }
    return result;
  }

  public static ArrayList<Coordinates> NucleotidesToListMapped(
      final ArrayList<Nucleotide> nucleotides, final int[] map, boolean primary) {
    ArrayList<Coordinates> result = new ArrayList<Coordinates>();
    if (primary) {
      // If map[i] == 1, take it.
      for (int i = 0; i < map.length; i++) {
        if (map[i] == 1) {
          for (final Coordinates rep : nucleotides.get(i).representatives) {
            result.add(rep);
          }
        }
      }
    } else {
      // Take it in the order stated in the map
      for (int i = 0; i < map.length; i++) {
        if (map[i] >= 0) {
          for (final Coordinates rep : nucleotides.get(map[i]).representatives) {
            result.add(rep);
          }
        }
      }
    }
    return result;
  }

  public final String getCode() {
    return code;
  }

  public final String getKey() {
    return key;
  }

  public String toString() {
    return key;
  }

  public final String getChain() {
    return chain;
  }

  public final String getNumber() {
    return number;
  }

  public final String getDelimitedChainNumber(final String delimiter) {
        return String.format("%s%s%s", this.chain, delimiter, this.number);
  }
}
