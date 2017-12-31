package tp.webservice.rest.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Vehicules")
@XmlAccessorType(XmlAccessType.FIELD)

public class Cars {

  @XmlElement(name = "vehicule")
  private List<Vehicule> entries;

  public List<Vehicule> entries() {
    return Collections.unmodifiableList(entries);
  }

  public void addEntry(Vehicule entry) {
    entries.add(entry);
  }

  public Cars() {
    entries = new ArrayList<Vehicule>();
  }

}
