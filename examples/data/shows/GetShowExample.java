package data.shows;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Show;
import com.wrapper.spotify.requests.data.shows.GetShowRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetShowExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String id = "5AvwZVawapvyhJUIx71pdJ";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetShowRequest getShowRequest = spotifyApi.getShow(id)
//          .market(CountryCode.SE)
    .build();

  public static void getShow_Sync() {
    try {
      final Show show = getShowRequest.execute();

      System.out.println("Name: " + show.getName());
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getShow_Async() {
    try {
      final CompletableFuture<Show> showFuture = getShowRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Show show = showFuture.join();

      System.out.println("Name: " + show.getName());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getShow_Sync();
    getShow_Async();
  }
}
