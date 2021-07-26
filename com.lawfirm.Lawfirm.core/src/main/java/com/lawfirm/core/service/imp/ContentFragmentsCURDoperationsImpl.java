package com.lawfirm.core.service.imp;

import java.util.Calendar;
import java.util.Iterator;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;
import com.adobe.cq.dam.cfm.FragmentData;
import com.lawfirm.core.service.ContentFragmentsCURDoperations;
import com.lawfirm.core.service.User;

@Component(service = ContentFragmentsCURDoperations.class)
public class ContentFragmentsCURDoperationsImpl implements ContentFragmentsCURDoperations {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(ContentFragmentsCURDoperationsImpl.class);
	@Reference
	private User user;
	private ResourceResolver resolver;

	@Override
	public JSONObject getContentFragment(String contentFragment) {
		String path = "/content/dam/Lawfirm/content-fragments/";
		JSONObject jsonObject = new JSONObject();
		JSONObject elements = new JSONObject();
		resolver = user.getUser();
		Resource resource = resolver.getResource(path + contentFragment);
		if (resource != null) {
			ContentFragment fragment = resource.adaptTo(ContentFragment.class);
			Iterator<ContentElement> iterator = fragment.getElements();
			try {
				while (iterator.hasNext()) {
					ContentElement element = iterator.next();
					FragmentData data = element.getValue();
					String dataType = data.getDataType().getTypeString();

					switch (dataType) {
						case "string": {
							elements.put(element.getName(), data.getValue().toString());
							break;
						}
						case "double": {
							elements.put(element.getName(), data.getValue().toString());
							break;
						}
						case "boolean": {
							elements.put(element.getName(), data.getValue().toString());
							break;
						}
						case "calendar": {
							Calendar calendar = (Calendar) data.getValue();
							String date = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-"
									+ calendar.get(Calendar.DATE) + " " + calendar.get(Calendar.HOUR) + ":"
									+ calendar.get(Calendar.MINUTE);
							elements.put(element.getName(), date);
							break;
						}
					}
				}
				jsonObject.put("", elements);
			} catch (JSONException e) {
				LOG.error(e.getMessage());
			}
		} else {
			LOG.error(contentFragment + " not found");
			jsonObject = null;
		}
		return jsonObject;
	}

}
