package dawidzior.bakingapp.model;

import org.parceler.Parcel;

import lombok.Getter;

@Parcel
@Getter
public class Step {

    long id;

    String shortDescription;

    String description;

    String videoURL;
}
