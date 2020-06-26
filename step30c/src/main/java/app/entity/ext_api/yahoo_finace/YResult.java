package app.entity.ext_api.yahoo_finace;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class YResult {
  private List<YItem> result;
}
