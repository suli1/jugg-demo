package com.small.lib.utils.logger;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.small.lib.utils.logger.Logger.DEBUG;
import static com.small.lib.utils.logger.Logger.ERROR;
import static com.small.lib.utils.logger.Logger.INFO;
import static com.small.lib.utils.logger.Logger.VERBOSE;
import static com.small.lib.utils.logger.Logger.WARN;
import static com.small.lib.utils.logger.Logger.ASSERT;


public class LoggerPrinter implements Printer {

    /**
     * It is used for json pretty print
     */
    private static final int JSON_INDENT = 2;

    /**
     * Provides one-time used tag for the log message
     */
    private final ThreadLocal<String> localTag = new ThreadLocal<>();

    private final List<LogAdapter> logAdapters = new ArrayList<>();

    @Override
    public Printer t(String tag) {
        if (tag != null) {
            localTag.set(tag);
        }
        return this;
    }

    @Override
    public void d(String message, Object... args) {
        log(DEBUG, null, message, args);
    }

    @Override
    public void d(Object object) {
        log(DEBUG, null, LogUtils.toString(object));
    }

    @Override
    public void e(String message, Object... args) {
        e(null, message, args);
    }

    @Override
    public void e(Throwable throwable, String message, Object... args) {
        log(ERROR, throwable, message, args);
    }

    @Override
    public void w(String message, Object... args) {
        log(WARN, null, message, args);
    }

    @Override
    public void i(String message, Object... args) {
        log(INFO, null, message, args);
    }

    @Override
    public void v(String message, Object... args) {
        log(VERBOSE, null, message, args);
    }

    @Override
    public void wtf(String message, Object... args) {
        log(ASSERT, null, message, args);
    }

    @Override
    public void json(String json) {
        if (TextUtils.isEmpty(json)) {
            d("Empty/Null json content");
            return;
        }
        try {
            json = json.trim();
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String message = jsonObject.toString(JSON_INDENT);
                d(message);
                return;
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String message = jsonArray.toString(JSON_INDENT);
                d(message);
                return;
            }
            e("Invalid Json");
        } catch (JSONException e) {
            e("Invalid Json");
        }
    }

//    @Override
//    public void xml(String xml) {
//        if (TextUtils.isEmpty(xml)) {
//            d("Empty/Null xml content");
//            return;
//        }
//        Writer writer = null;
//        try {
//            Source xmlInput = new StreamSource(new StringReader(xml));
//            StreamResult xmlOutput = new StreamResult(new StringWriter());
////            Transformer transformer = TransformerFactory.newInstance().newTransformer();
//            TransformerFactory transFact = TransformerFactory.newInstance();
//            transFact.setFeature("http://xml.org/sax/features/external-general-entities", false);
//            transFact.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
//            transFact.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
//            transFact.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
//            Transformer transformer = transFact.newTransformer();
//
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//            transformer.transform(xmlInput, xmlOutput);
//            writer = xmlOutput.getWriter();
//            d(writer.toString().replaceFirst(">", ">\n"));
//        } catch (TransformerException e) {
//            e("Invalid xml");
//        } finally {
//            CloseUtils.closeIO(writer);
//        }
//    }

    @Override
    public synchronized void log(int priority, String tag, String message, Throwable throwable) {
        if (throwable != null && message != null) {
            message += " : " + LogUtils.getStackTraceString(throwable);
        }
        if (throwable != null && message == null) {
            message = LogUtils.getStackTraceString(throwable);
        }
        if (TextUtils.isEmpty(message)) {
            message = "Empty/NULL log message";
        }

        for (LogAdapter adapter : logAdapters) {
            if (adapter.isLoggable(priority, tag)) {
                adapter.log(priority, tag, message);
            }
        }
    }

    @Override
    public void clearLogAdapters() {
        logAdapters.clear();
    }

    @Override
    public void addAdapter(LogAdapter adapter) {
        logAdapters.add(adapter);
    }

    /**
     * This method is synchronized in order to avoid messy of logs' order.
     */
    private synchronized void log(int priority, Throwable throwable, String msg, Object... args) {
        String tag = getTag();
        String message = createMessage(msg, args);
        log(priority, tag, message, throwable);
    }

    /**
     * @return the appropriate tag based on local or global
     */
    private String getTag() {
        String tag = localTag.get();
        if (tag != null) {
            localTag.remove();
            return tag;
        }
        return null;
    }

    private String createMessage(String message, Object... args) {
        return args == null || args.length == 0 ? message : String.format(message, args);
    }
}