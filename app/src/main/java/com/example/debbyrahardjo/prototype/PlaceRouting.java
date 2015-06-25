package com.example.debbyrahardjo.prototype;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PlaceRouting extends AbstractRouting<String> {

    public PlaceRouting(TravelMode mTravelMode) {
        super(mTravelMode);
    }

    protected String constructURL(String... points) {
        final StringBuffer mBuf = new StringBuffer(AbstractRouting.DIRECTIONS_API_URL);
        String from = null, to = null;
        try {
            from = URLEncoder.encode(points[0], "utf-8");
            to = URLEncoder.encode(points[1], "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        mBuf.append("origin=");
        mBuf.append(from);
        mBuf.append("&destination=");
        mBuf.append(to);
        mBuf.append("&sensor=true&mode=");
        mBuf.append(_mTravelMode.getValue());

        return mBuf.toString();
    }
}
