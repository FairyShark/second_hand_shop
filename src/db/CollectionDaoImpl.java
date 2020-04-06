package db;

import bean.Collection;
import dao.CollectionDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CollectionDaoImpl implements CollectionDao {

    private Connection conn = null;

    private PreparedStatement pstmt = null;

    public CollectionDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean addCollectionGoods(int uid, int gid) throws Exception {
        pstmt = null;
        String sql = "insert into collection(uid,gid,collecttime)value(?,?,now());";
        int result = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        pstmt.setInt(2, gid);
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCollectionGoods(int uid, int gid) throws Exception {
        String sql = "delete from collection where uid=? and gid=?";
        int result = 0;
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        pstmt.setInt(2, gid);
        result = pstmt.executeUpdate();
        pstmt.close();
        if (result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean judgeCollection(int uid, int gid) throws Exception {
        pstmt = null;
        ResultSet rs = null;
        String sql = "select * from collection where uid=? and gid=?";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        pstmt.setInt(2, gid);
        rs = pstmt.executeQuery();
        if(rs.next()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Collection> getAllCollectionGoods(int uid) throws Exception {
        pstmt = null;
        ResultSet rs = null;
        List<Collection> cList = null;
        String sql = "select * from collection where uid=? order by collecttime desc";
        pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, uid);
        rs = pstmt.executeQuery();
        Collection ct;
        cList = new ArrayList<Collection>();
        while (rs.next()) {
            ct = new Collection();
            ct.setUid(uid);
            ct.setCid(rs.getInt("cid"));
            ct.setGid(rs.getInt("gid"));
            ct.setCollecttime(rs.getString("collecttime"));
            cList.add(ct);
        }
        return cList;
    }

}