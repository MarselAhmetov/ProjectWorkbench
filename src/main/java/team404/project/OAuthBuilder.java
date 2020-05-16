package team404.project;

import bell.oauth.discord.domain.Connection;
import bell.oauth.discord.domain.Guild;
import bell.oauth.discord.domain.User;
import bell.oauth.discord.main.Response;
import bell.oauth.discord.req.Post;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OAuthBuilder {
    private static final String BASEURI = "https://discordapp.com/api/";
    private static final String TOKENURI = "oauth2/token";
    private static final String REVOCATIONURI = "oauth2/token/revoke";
    private static final String CONNECTIONSURI = "users/@me/connections";
    private static final String MEURI = "users/@me";
    private static final String GUILDURI = "users/@me/guilds";
    private OkHttpClient client;
    private String id;
    private String secret;
    private String redirect;
    private String scopes;
    private String access_token;
    private String refresh_token;

    public OAuthBuilder(String clientID, String clientSecret) {
        this.id = clientID;
        this.secret = clientSecret;
        this.client = new OkHttpClient();
    }

    public OAuthBuilder setRedirectURI(String url) {
        this.redirect = url;
        return this;
    }

    public OAuthBuilder setScopes(String[] scopes) {
        this.scopes = "";
        String[] var2 = scopes;
        int var3 = scopes.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String scope = var2[var4];
            this.scopes = this.scopes + scope + "%20";
        }

        this.scopes = this.scopes.substring(0, this.scopes.length() - 3);
        return this;
    }

    public String getAuthorizationUrl(String state) {
        StringBuilder builder = new StringBuilder();
        builder.append("https://discordapp.com/api/");
        builder.append("oauth2/authorize");
        builder.append("?response_type=code");
        builder.append("&client_id=" + this.id);
        builder.append("&scope=" + this.scopes);
        if (state != null && state.length() > 0) {
            builder.append("&state=" + state);
        }

        builder.append("&redirect_uri=" + this.redirect);
        return builder.toString();
    }

    public Response exchange(String code) {
        try {
            String json = Post.exchangePost(this.client, "https://discordapp.com/api/oauth2/token", this.id, this.secret, code, this.redirect);
            JSONObject js = new JSONObject(json);

            try {
                this.access_token = js.getString("access_token");
                this.refresh_token = js.getString("refresh_token");
                return Response.OK;
            } catch (JSONException var5) {
                return Response.ERROR;
            }
        } catch (IOException var6) {
            return Response.ERROR;
        }
    }

    public Response refresh() {
        try {
            String json = Post.refreshPost(this.client, "https://discordapp.com/api/oauth2/token", this.id, this.secret, this.refresh_token, this.redirect);
            JSONObject js = new JSONObject(json);

            try {
                this.access_token = js.getString("access_token");
                this.refresh_token = js.getString("refresh_token");
                return Response.OK;
            } catch (JSONException var4) {
                return Response.ERROR;
            }
        } catch (IOException var5) {
            return Response.ERROR;
        }
    }

    public Response revoke() {
        try {
            Post.revokePost(this.client, "https://discordapp.com/api/oauth2/token/revoke", this.access_token);
            return Response.OK;
        } catch (IOException var2) {
            return Response.ERROR;
        }
    }

    public User getUser() {
        User user = new User();

        try {
            String json = Post.get(this.client, "https://discordapp.com/api/users/@me", this.access_token);
            JSONObject js = new JSONObject(json);

            try {
                user.setId(js.getString("id"));
                user.setAvatar(js.getString("avatar"));
                user.setBot(js.getBoolean("bot"));
                user.setDiscriminator(js.getString("discriminator"));
                user.setEmail(js.getString("email"));
                user.setMfa_enabled(js.getBoolean("mfa_enabled"));
                user.setUsername(js.getString("username"));
                user.setVerified(js.getBoolean("verified"));
            } catch (JSONException var5) {
                user.setId(js.getString("id"));
                user.setEmail(js.getString("email"));
                user.setUsername(js.getString("username"));
            }

            return user;
        } catch (IOException var6) {
            return null;
        }
    }

    public List<Guild> getGuilds() {
        ArrayList guilds = new ArrayList();

        try {
            String json = Post.get(this.client, "https://discordapp.com/api/users/@me/guilds", this.access_token);
            JSONArray arrJs = new JSONArray(json);
            Iterator var4 = arrJs.iterator();

            while(var4.hasNext()) {
                Object guild = var4.next();
                Guild g = new Guild();
                JSONObject obj = (JSONObject)guild;
                g.setIcon(obj.getString("icon"));
                g.setId(obj.getString("id"));
                g.setName(obj.getString("name"));
                g.setOwner(obj.getBoolean("owner"));
                g.setPermissions(obj.getInt("permissions"));
                guilds.add(g);
            }

            return guilds;
        } catch (IOException var8) {
            return null;
        }
    }

    public List<Connection> getConnections() {
        ArrayList connections = new ArrayList();

        try {
            String json = Post.get(this.client, "https://discordapp.com/api/users/@me/connections", this.access_token);
            JSONArray arrJs = new JSONArray(json);
            Iterator var4 = arrJs.iterator();

            while(var4.hasNext()) {
                Object connection = var4.next();
                Connection c = new Connection();
                JSONObject obj = (JSONObject)connection;
                c.setFriend_sync(obj.getBoolean("friend_sync"));
                c.setId(obj.getString("id"));
                c.setName(obj.getString("name"));
                c.setType(obj.getString("type"));
                c.setVerified(obj.getBoolean("verified"));
                c.setVisibility(obj.getInt("visibility"));
                connections.add(c);
            }

            return connections;
        } catch (IOException var8) {
            return null;
        }
    }

    public String getScopes() {
        return this.scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public String getAccess_token() {
        return this.access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return this.refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}

